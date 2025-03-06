package com.yue.surfbrowser;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import fi.iki.elonen.NanoHTTPD;

// 添加依赖：implementation 'org.nanohttpd:nanohttpd:2.3.1'
public class LocalHttpServer extends NanoHTTPD {
    private Context context;

    public LocalHttpServer(int port, Context context) {
        super(port);
        this.context = context;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        try {
            // 从assets目录读取文件
            InputStream inputStream = context.getAssets().open("www" + uri);
            String mimeType = "text/html";
            if (uri.endsWith(".css")) mimeType = "text/css";
            else if (uri.endsWith(".js")) mimeType = "application/javascript";
            return newFixedLengthResponse(Response.Status.OK, mimeType, inputStream, inputStream.available());
        } catch (IOException e) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "File not found");
        }
    }
}