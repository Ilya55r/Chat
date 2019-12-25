//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `Chat.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.zeroc.demos.Chat;

/**
 * The InvalidMessageException is raised when a user sends an invalid
 * message to the server. A message is considered invalid if the
 * message size exceeds the maximum message size.
 **/
public class InvalidMessageException extends com.zeroc.Ice.UserException
{
    public InvalidMessageException()
    {
        this.reason = "";
    }

    public InvalidMessageException(Throwable cause)
    {
        super(cause);
        this.reason = "";
    }

    public InvalidMessageException(String reason)
    {
        this.reason = reason;
    }

    public InvalidMessageException(String reason, Throwable cause)
    {
        super(cause);
        this.reason = reason;
    }

    public String ice_id()
    {
        return "::Chat::InvalidMessageException";
    }

    /**
     * The reason why the message was rejected by the server.
     **/
    public String reason;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::Chat::InvalidMessageException", -1, true);
        ostr_.writeString(reason);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        reason = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = 1256244027L;
}