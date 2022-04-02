// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.std.impl.basic_string;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXPointer;
import com.alibaba.fastffi.CXXValue;
import com.alibaba.fastffi.FFIExpr;
import com.alibaba.fastffi.FFIFactory;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.__wrap_iter;
import io.v6d.std.impl.__wrap_iter.IteratorTypeChar;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("std::basic_string<char, std::char_traits<char>, std::allocator<char>>::iterator")
@FFIGen
@CXXHead(
        system = "string"
)
public interface IteratorCharStdCharTraitsCharStdAllocatorChar extends CXXPointer {
    @FFIExpr("{0}")
    __wrap_iter<PointerCharStdCharTraitsCharStdAllocatorChar> get();

    @CXXValue
    IteratorTypeChar base();

    static IteratorCharStdCharTraitsCharStdAllocatorChar cast(final long __foreign_address) {
        try {
            Class<IteratorCharStdCharTraitsCharStdAllocatorChar> clz = (Class<IteratorCharStdCharTraitsCharStdAllocatorChar>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(IteratorCharStdCharTraitsCharStdAllocatorChar.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static IteratorCharStdCharTraitsCharStdAllocatorChar cast(final FFIPointer __foreign_pointer) {
        return IteratorCharStdCharTraitsCharStdAllocatorChar.cast(__foreign_pointer.getAddress());
    }

    static Factory getFactory() {
        return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(IteratorCharStdCharTraitsCharStdAllocatorChar.class, true));
    }

    static IteratorCharStdCharTraitsCharStdAllocatorChar create() {
        return IteratorCharStdCharTraitsCharStdAllocatorChar.getFactory().create();
    }

    @FFIFactory
    @CXXHead(
            system = "string"
    )
    interface Factory {
        IteratorCharStdCharTraitsCharStdAllocatorChar create();
    }
}