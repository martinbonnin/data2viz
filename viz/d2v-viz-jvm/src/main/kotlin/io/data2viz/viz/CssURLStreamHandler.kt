package io.data2viz.viz

import java.io.InputStream
import java.io.StringWriter
import java.net.*
import java.nio.charset.StandardCharsets
import java.util.*


val StyleSheet.url: String
    get() = "css://${this.javaClass.canonicalName}"


fun registerURLStreamHandlerFactory() {
    URL.setURLStreamHandlerFactory(CssURLStreamHandler.HandlerFactory())
}

class CssURLStreamHandler : URLStreamHandler() {
    
    override fun openConnection(url: URL): URLConnection = CSSURLConnection(url)

    class CSSURLConnection(url: URL) : URLConnection(url) {
        override fun connect() { }
        override fun getInputStream(): InputStream {
            val stylesheet = Class.forName(url.host).newInstance() as StyleSheet
            val rendered = stylesheet.render()
            return rendered.byteInputStream(StandardCharsets.UTF_8)
        }
    }

    class HandlerFactory : URLStreamHandlerFactory {
        override fun createURLStreamHandler(protocol: String) =
            if ("css" == protocol) CssURLStreamHandler() else null
    }
}
