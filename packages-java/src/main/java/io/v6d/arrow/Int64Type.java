// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.arrow;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFILibrary;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.CChar;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("arrow::Int64Type")
@FFIGen
@CXXHead(
        system = "arrow/type.h"
)
public interface Int64Type extends FFIPointer {
    static Int64Type cast(final long __foreign_address) {
        try {
            Class<Int64Type> clz = (Class<Int64Type>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(Int64Type.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static Int64Type cast(final FFIPointer __foreign_pointer) {
        return Int64Type.cast(__foreign_pointer.getAddress());
    }

    @FFIGen
    @FFILibrary(
            value = "arrow::Int64Type",
            namespace = "arrow::Int64Type"
    )
    @CXXHead(
            system = "arrow/type.h"
    )
    interface Library {
        Library INSTANCE = FFITypeFactory.getLibrary(Library.class);

        CChar type_name();
    }
}