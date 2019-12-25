//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `ChatSession.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.zeroc.demos.Chat;

/**
 * The ChatRoomCallback interface is the interface that clients implement
 * as their callback object.
 *
 * The server calls operations of this interface to communicate
 * with connected clients.
 **/
public interface ChatRoomCallbackPrx extends com.zeroc.Ice.ObjectPrx
{
    /**
     * The server invokes this operation when the client sets the callback
     * for a session. This provides the client with the initial list of users
     * currently in the chat room.
     * @param users The names of users currently in the chat room.
     **/
    default void init(String[] users)
    {
        init(users, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    /**
     * The server invokes this operation when the client sets the callback
     * for a session. This provides the client with the initial list of users
     * currently in the chat room.
     * @param users The names of users currently in the chat room.
     * @param context The Context map to send with the invocation.
     **/
    default void init(String[] users, java.util.Map<String, String> context)
    {
        _iceI_initAsync(users, context, true).waitForResponse();
    }

    /**
     * The server invokes this operation when the client sets the callback
     * for a session. This provides the client with the initial list of users
     * currently in the chat room.
     * @param users The names of users currently in the chat room.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> initAsync(String[] users)
    {
        return _iceI_initAsync(users, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    /**
     * The server invokes this operation when the client sets the callback
     * for a session. This provides the client with the initial list of users
     * currently in the chat room.
     * @param users The names of users currently in the chat room.
     * @param context The Context map to send with the invocation.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> initAsync(String[] users, java.util.Map<String, String> context)
    {
        return _iceI_initAsync(users, context, false);
    }

    /**
     * @hidden
     * @param iceP_users -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_initAsync(String[] iceP_users, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "init", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeStringSeq(iceP_users);
                 }, null);
        return f;
    }

    /**
     * The server invokes this operation to deliver a message
     * that was sent to the chat room.
     * @param timestamp The time at which the message was sent.
     * @param name The name of the user that send the message.
     * @param message The contents of the message.
     **/
    default void send(long timestamp, String name, String message)
    {
        send(timestamp, name, message, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    /**
     * The server invokes this operation to deliver a message
     * that was sent to the chat room.
     * @param timestamp The time at which the message was sent.
     * @param name The name of the user that send the message.
     * @param message The contents of the message.
     * @param context The Context map to send with the invocation.
     **/
    default void send(long timestamp, String name, String message, java.util.Map<String, String> context)
    {
        _iceI_sendAsync(timestamp, name, message, context, true).waitForResponse();
    }

    /**
     * The server invokes this operation to deliver a message
     * that was sent to the chat room.
     * @param timestamp The time at which the message was sent.
     * @param name The name of the user that send the message.
     * @param message The contents of the message.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> sendAsync(long timestamp, String name, String message)
    {
        return _iceI_sendAsync(timestamp, name, message, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    /**
     * The server invokes this operation to deliver a message
     * that was sent to the chat room.
     * @param timestamp The time at which the message was sent.
     * @param name The name of the user that send the message.
     * @param message The contents of the message.
     * @param context The Context map to send with the invocation.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> sendAsync(long timestamp, String name, String message, java.util.Map<String, String> context)
    {
        return _iceI_sendAsync(timestamp, name, message, context, false);
    }

    /**
     * @hidden
     * @param iceP_timestamp -
     * @param iceP_name -
     * @param iceP_message -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_sendAsync(long iceP_timestamp, String iceP_name, String iceP_message, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "send", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeLong(iceP_timestamp);
                     ostr.writeString(iceP_name);
                     ostr.writeString(iceP_message);
                 }, null);
        return f;
    }

    /**
     * The server invokes this operation when a user joins
     * the chat room.
     * @param timestamp The time at which the user joined the chat room.
     * @param name The name of the user that joined the chat room.
     **/
    default void join(long timestamp, String name)
    {
        join(timestamp, name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    /**
     * The server invokes this operation when a user joins
     * the chat room.
     * @param timestamp The time at which the user joined the chat room.
     * @param name The name of the user that joined the chat room.
     * @param context The Context map to send with the invocation.
     **/
    default void join(long timestamp, String name, java.util.Map<String, String> context)
    {
        _iceI_joinAsync(timestamp, name, context, true).waitForResponse();
    }

    /**
     * The server invokes this operation when a user joins
     * the chat room.
     * @param timestamp The time at which the user joined the chat room.
     * @param name The name of the user that joined the chat room.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> joinAsync(long timestamp, String name)
    {
        return _iceI_joinAsync(timestamp, name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    /**
     * The server invokes this operation when a user joins
     * the chat room.
     * @param timestamp The time at which the user joined the chat room.
     * @param name The name of the user that joined the chat room.
     * @param context The Context map to send with the invocation.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> joinAsync(long timestamp, String name, java.util.Map<String, String> context)
    {
        return _iceI_joinAsync(timestamp, name, context, false);
    }

    /**
     * @hidden
     * @param iceP_timestamp -
     * @param iceP_name -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_joinAsync(long iceP_timestamp, String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "join", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeLong(iceP_timestamp);
                     ostr.writeString(iceP_name);
                 }, null);
        return f;
    }

    /**
     * The servers invokes this operation when a user leaves
     * the chat room.
     * @param timestamp The time at which the user left the chat room.
     * @param name The name of the user that left the chat room.
     **/
    default void leave(long timestamp, String name)
    {
        leave(timestamp, name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    /**
     * The servers invokes this operation when a user leaves
     * the chat room.
     * @param timestamp The time at which the user left the chat room.
     * @param name The name of the user that left the chat room.
     * @param context The Context map to send with the invocation.
     **/
    default void leave(long timestamp, String name, java.util.Map<String, String> context)
    {
        _iceI_leaveAsync(timestamp, name, context, true).waitForResponse();
    }

    /**
     * The servers invokes this operation when a user leaves
     * the chat room.
     * @param timestamp The time at which the user left the chat room.
     * @param name The name of the user that left the chat room.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> leaveAsync(long timestamp, String name)
    {
        return _iceI_leaveAsync(timestamp, name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    /**
     * The servers invokes this operation when a user leaves
     * the chat room.
     * @param timestamp The time at which the user left the chat room.
     * @param name The name of the user that left the chat room.
     * @param context The Context map to send with the invocation.
     * @return A future that will be completed when the invocation completes.
     **/
    default java.util.concurrent.CompletableFuture<Void> leaveAsync(long timestamp, String name, java.util.Map<String, String> context)
    {
        return _iceI_leaveAsync(timestamp, name, context, false);
    }

    /**
     * @hidden
     * @param iceP_timestamp -
     * @param iceP_name -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_leaveAsync(long iceP_timestamp, String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "leave", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeLong(iceP_timestamp);
                     ostr.writeString(iceP_name);
                 }, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ChatRoomCallbackPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ChatRoomCallbackPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ChatRoomCallbackPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ChatRoomCallbackPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static ChatRoomCallbackPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static ChatRoomCallbackPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, ChatRoomCallbackPrx.class, _ChatRoomCallbackPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default ChatRoomCallbackPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (ChatRoomCallbackPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default ChatRoomCallbackPrx ice_adapterId(String newAdapterId)
    {
        return (ChatRoomCallbackPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default ChatRoomCallbackPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (ChatRoomCallbackPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default ChatRoomCallbackPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (ChatRoomCallbackPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default ChatRoomCallbackPrx ice_invocationTimeout(int newTimeout)
    {
        return (ChatRoomCallbackPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default ChatRoomCallbackPrx ice_connectionCached(boolean newCache)
    {
        return (ChatRoomCallbackPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default ChatRoomCallbackPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (ChatRoomCallbackPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ChatRoomCallbackPrx ice_secure(boolean b)
    {
        return (ChatRoomCallbackPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default ChatRoomCallbackPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (ChatRoomCallbackPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ChatRoomCallbackPrx ice_preferSecure(boolean b)
    {
        return (ChatRoomCallbackPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default ChatRoomCallbackPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (ChatRoomCallbackPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default ChatRoomCallbackPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (ChatRoomCallbackPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default ChatRoomCallbackPrx ice_collocationOptimized(boolean b)
    {
        return (ChatRoomCallbackPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default ChatRoomCallbackPrx ice_twoway()
    {
        return (ChatRoomCallbackPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default ChatRoomCallbackPrx ice_oneway()
    {
        return (ChatRoomCallbackPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default ChatRoomCallbackPrx ice_batchOneway()
    {
        return (ChatRoomCallbackPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default ChatRoomCallbackPrx ice_datagram()
    {
        return (ChatRoomCallbackPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default ChatRoomCallbackPrx ice_batchDatagram()
    {
        return (ChatRoomCallbackPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default ChatRoomCallbackPrx ice_compress(boolean co)
    {
        return (ChatRoomCallbackPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default ChatRoomCallbackPrx ice_timeout(int t)
    {
        return (ChatRoomCallbackPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default ChatRoomCallbackPrx ice_connectionId(String connectionId)
    {
        return (ChatRoomCallbackPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default ChatRoomCallbackPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (ChatRoomCallbackPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::Chat::ChatRoomCallback";
    }
}
