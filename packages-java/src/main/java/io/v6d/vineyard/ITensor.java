// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.vineyard;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("vineyard::ITensor")
@FFIGen
@CXXHead("basic/ds/tensor.vineyard.h")
public interface ITensor extends Object, FFIPointer {
    static ITensor cast(final long __foreign_address) {
        try {
            Class<ITensor> clz = (Class<ITensor>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(ITensor.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static ITensor cast(final FFIPointer __foreign_pointer) {
        return ITensor.cast(__foreign_pointer.getAddress());
    }
}