//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

#pragma once

["java:package:com.zeroc.demos.Ice.invoke"]
module Demo
{
    exception PrintFailure
    {
        string reason;
    }

    sequence<string> StringSeq;

    dictionary<string, string> StringDict;

    enum Color { red, green, blue }

    struct Structure
    {
        string name;
        Color value;
    }

    sequence<Structure> StructureSeq;

    class C
    {
        Structure s;
    }

    interface Printer
    {
        void printString(string message);
        void printStringSequence(StringSeq seq);
        void printDictionary(StringDict dict);
        void printEnum(Color c);
        void printStruct(Structure st);
        void printStructSequence(StructureSeq seq);
        void printClass(C cls);
        C getValues(out string str);
        void throwPrintFailure() throws PrintFailure;
        void shutdown();
    }
}
