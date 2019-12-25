//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

package com.zeroc.demos.Chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ChatUtils
{
    //
    // This function unescapes HTML entities in the data string
    // and return the unescaped string.
    //
    public static String unstripHtml(String data)
    {
        data = data.replace("&quot;", "\"");
        data = data.replace("&#39;", "'");
        data = data.replace("&lt;", "<");
        data = data.replace("&gt;", ">");
        data = data.replace("&amp;", "&");
        return data;
    }

    public static String formatTimestamp(long timestamp)
    {
        DateFormat dtf = new SimpleDateFormat("HH:mm:ss", java.util.Locale.US);
        dtf.setTimeZone(TimeZone.getDefault());
        return dtf.format(new Date(timestamp));
    }
}
