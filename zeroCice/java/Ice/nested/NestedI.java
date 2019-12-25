//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

import com.zeroc.demos.Ice.nested.Demo.*;

class NestedI implements Nested
{
    NestedI(NestedPrx self)
    {
        _self = self;
    }

    @Override
    public void nestedCall(int level, NestedPrx proxy, com.zeroc.Ice.Current current)
    {
        System.out.println("" + level);
        if(--level > 0)
        {
            //
            // Ensure the invocation times out if the nesting level is too
            // high and there are no more threads in the thread pool to
            // dispatch the call.
            //
            proxy.ice_invocationTimeout(5000).nestedCall(level, _self);
        }
    }

    private NestedPrx _self;
}
