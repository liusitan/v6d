// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.vineyard;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXReference;
import com.alibaba.fastffi.CXXValue;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFINameAlias;
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

@FFITypeAlias("vineyard::Scalar")
@FFIGen
@CXXHead("basic/ds/scalar.vineyard.h")
public interface Scalar<T> extends Registered<Scalar<T>>, FFIPointer {
    @FFINameAlias("Construct")
    void Construct_1(@CXXReference ObjectMeta meta);

    @CXXValue
    T Value();

    @CXXValue
    @FFITypeAlias("vineyard::AnyType")
    AnyType Type();

    static <T> Scalar<T> cast(Class<T> __t, final long __foreign_address) {
        try {
            Class<Scalar<T>> clz = (Class<Scalar<T>>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(FFITypeFactory.makeParameterizedType(Scalar.class, __t), true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static <T> Scalar<T> cast(Class<T> __t, final FFIPointer __foreign_pointer) {
        return Scalar.cast(__t, __foreign_pointer.getAddress());
    }
}