//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

package com.zeroc.hello;

import com.zeroc.Ice.ObjectPrx;

enum DeliveryMode
{
    TWOWAY,
    TWOWAY_SECURE,
    ONEWAY,
    ONEWAY_BATCH,
    ONEWAY_SECURE,
    ONEWAY_SECURE_BATCH,
    DATAGRAM,
    DATAGRAM_BATCH;

    ObjectPrx apply(ObjectPrx prx)
    {
        switch (this)
        {
            case TWOWAY:
            {
                prx = prx.ice_twoway();
                break;
            }
            case TWOWAY_SECURE:
            {
                prx = prx.ice_twoway().ice_secure(true);
                break;
            }
            case ONEWAY:
            {
                prx = prx.ice_oneway();
                break;
            }
            case ONEWAY_BATCH:
            {
                prx = prx.ice_batchOneway();
                break;
            }
            case ONEWAY_SECURE:
            {
                prx = prx.ice_oneway().ice_secure(true);
                break;
            }
            case ONEWAY_SECURE_BATCH:
            {
                prx = prx.ice_batchOneway().ice_secure(true);
                break;
            }
            case DATAGRAM:
            {
                prx = prx.ice_datagram();
                break;
            }
            case DATAGRAM_BATCH:
            {
                prx = prx.ice_batchDatagram();
                break;
            }
        }
        return prx;
    }

    public boolean isOneway()
    {
        return this == ONEWAY || this == ONEWAY_SECURE || this == DATAGRAM;
    }

    public boolean isBatch()
    {
        return this == ONEWAY_BATCH || this == DATAGRAM_BATCH || this == ONEWAY_SECURE_BATCH;
    }
}
