package com.linksi.app.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

data class ReaderArticle(
    val title: String,
    val byline: String,
    val paragraphs: List<String>
)

/**
 * Lightweight readability extraction: fetches the page and pulls out the
 * main article text (title + paragraphs) for the distraction-free reader view.
 */
object ReaderExtractor {

    private const val USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"

    suspend fun extract(url: String): ReaderArticle? = withContext(Dispatchers.IO) {
        try {
            val doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(15000)
                .followRedirects(true)
                .get()

            doc.select("script, style, nav, header, footer, aside, form, noscript, iframe, svg")
                .remove()

            val title = doc.selectFirst("article h1, [property='og:title']")?.text()
                ?: doc.selectFirst("h1")?.text()
                ?: doc.title()

            val byline = doc.selectFirst("[rel='author'], [property='article:author'], .byline, .author")?.text() ?: ""

            val root: Element = doc.selectFirst("article")
                ?: doc.selectFirst("[role='main'], main, #content, .post-content, .article-content, .entry-content")
                ?: doc.body()
                ?: return@withContext null

            val paragraphs = root.select("p, h2, h3, blockquote, li")
                .map { it.text().trim() }
                .filter { it.length > 40 }
                .distinct()

            if (paragraphs.isEmpty()) return@withContext null

            ReaderArticle(
                title = title.trim(),
                byline = byline.trim(),
                paragraphs = paragraphs
            )
        } catch (e: Exception) {
            null
        }
    }
}
