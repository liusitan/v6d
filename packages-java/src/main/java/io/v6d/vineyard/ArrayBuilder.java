// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.vineyard;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXReference;
import com.alibaba.fastffi.CXXValue;
import com.alibaba.fastffi.FFIFactory;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFINameAlias;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.vector;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("vineyard::ArrayBuilder")
@FFIGen
@CXXHead("basic/ds/array.h")
public interface ArrayBuilder<T> extends ArrayBaseBuilder<T>, FFIPointer {
    long size();

    T data();

    @FFINameAlias("data")
    T data_1();

    @CXXValue
    @FFINameAlias("Build")
    Status Build_1(@CXXReference Client client);

    static <T> ArrayBuilder<T> cast(Class<T> __t, final long __foreign_address) {
        try {
            Class<ArrayBuilder<T>> clz = (Class<ArrayBuilder<T>>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(FFITypeFactory.makeParameterizedType(ArrayBuilder.class, __t), true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static <T> ArrayBuilder<T> cast(Class<T> __t, final FFIPointer __foreign_pointer) {
        return ArrayBuilder.cast(__t, __foreign_pointer.getAddress());
    }

    static <T> Factory<T> getFactory(Class<T> __t) {
        return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(FFITypeFactory.makeParameterizedType(ArrayBuilder.class, __t), true));
    }

    static <T> ArrayBuilder<T> create(Class<T> __t, @CXXReference Client client, long size) {
        return ArrayBuilder.getFactory(__t).create(client, size);
    }

    static <T> ArrayBuilder<T> create(Class<T> __t, @CXXReference Client client,
            @CXXReference vector<T> vec) {
        return ArrayBuilder.getFactory(__t).create(client, vec);
    }

    static <T> ArrayBuilder<T> create(Class<T> __t, @CXXReference Client client, T data,
            long size) {
        return ArrayBuilder.getFactory(__t).create(client, data, size);
    }

    @FFIFactory
    @CXXHead("basic/ds/array.h")
    interface Factory<T> {
        ArrayBuilder<T> create(@CXXReference Client client, long size);

        ArrayBuilder<T> create(@CXXReference Client client, @CXXReference vector<T> vec);

        ArrayBuilder<T> create(@CXXReference Client client, T data, long size);
    }
}