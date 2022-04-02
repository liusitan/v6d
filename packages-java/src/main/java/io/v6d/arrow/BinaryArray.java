// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.arrow;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXReference;
import com.alibaba.fastffi.FFIFactory;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.shared_ptr;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("arrow::BinaryArray")
@FFIGen
@CXXHead(
        system = "arrow/array/array_binary.h"
)
public interface BinaryArray extends BaseBinaryArray<BinaryType>, FFIPointer {
    static BinaryArray cast(final long __foreign_address) {
        try {
            Class<BinaryArray> clz = (Class<BinaryArray>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(BinaryArray.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static BinaryArray cast(final FFIPointer __foreign_pointer) {
        return BinaryArray.cast(__foreign_pointer.getAddress());
    }

    static Factory getFactory() {
        return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(BinaryArray.class, true));
    }

    static BinaryArray create(
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::ArrayData>") shared_ptr<ArrayData> data) {
        return BinaryArray.getFactory().create(data);
    }

    static BinaryArray create(long length,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> value_offsets,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
            long null_count, long offset) {
        return BinaryArray.getFactory().create(length, value_offsets, data, null_bitmap, null_count, offset);
    }

    @FFIFactory
    @CXXHead(
            system = "arrow/array/array_binary.h"
    )
    interface Factory {
        BinaryArray create(
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::ArrayData>") shared_ptr<ArrayData> data);

        BinaryArray create(long length,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> value_offsets,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
                long null_count, long offset);
    }
}