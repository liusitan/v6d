// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.std.impl.vector;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXPointer;
import com.alibaba.fastffi.FFIExpr;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.reverse_iterator;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("std::vector<std::shared_ptr<arrow::Scalar>, std::allocator<std::shared_ptr<arrow::Scalar>>>::const_reverse_iterator")
@FFIGen
@CXXHead(
        system = "vector"
)
public interface ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar extends CXXPointer {
    @FFIExpr("{0}")
    reverse_iterator<ConstIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar> get();

    static ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar cast(
            final long __foreign_address) {
        try {
            Class<ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar> clz = (Class<ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar cast(
            final FFIPointer __foreign_pointer) {
        return ConstReverseIteratorStdSharedPtrArrowScalarStdAllocatorStdSharedPtrArrowScalar.cast(__foreign_pointer.getAddress());
    }
}