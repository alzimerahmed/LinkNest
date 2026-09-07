package com.linksi.app.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

data class LinkHealthReport(
    val checked: Int,
    val deadLinkIds: List<Long>
)

/**
 * Batched link health checker: issues lightweight HEAD (fallback GET) requests
 * and reports links that are unreachable or return permanent client errors.
 */
object LinkHealthChecker {

    suspend fun check(links: List<com.linksi.app.domain.model.Link>, maxConcurrency: Int = 8): LinkHealthReport =
        withContext(Dispatchers.IO) {
            val dead = coroutineScope {
                links.chunked(maxConcurrency).flatMap { batch ->
                    batch.map { link -> async { if (isDead(link.url)) link.id else null } }
                        .awaitAll()
                        .filterNotNull()
                }
            }
            LinkHealthReport(checked = links.size, deadLinkIds = dead)
        }

    private fun isDead(url: String): Boolean {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.instanceFollowRedirects = true
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            connection.requestMethod = "HEAD"
            var code = connection.responseCode
            // Some servers reject HEAD — retry with GET
            if (code in 400..499 || code >= 500) {
                connection.disconnect()
                val get = URL(url).openConnection() as HttpURLConnection
                get.instanceFollowRedirects = true
                get.connectTimeout = 8000
                get.readTimeout = 8000
                get.requestMethod = "GET"
                code = get.responseCode
                get.disconnect()
            }
            connection.disconnect()
            code == 404 || code == 410 || code == 0
        } catch (e: Exception) {
            true
        }
    }
}
