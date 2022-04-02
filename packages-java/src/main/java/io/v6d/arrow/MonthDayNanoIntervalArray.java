// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.arrow;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXPointer;
import com.alibaba.fastffi.CXXReference;
import com.alibaba.fastffi.CXXValue;
import com.alibaba.fastffi.FFIExpr;
import com.alibaba.fastffi.FFIFactory;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFILibrary;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.CChar;
import io.v6d.std.CUnsignedChar;
import io.v6d.std.shared_ptr;
import io.v6d.std.string;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("arrow::MonthDayNanoIntervalArray")
@FFIGen
@CXXHead(
        system = "arrow/array/array_primitive.h"
)
public interface MonthDayNanoIntervalArray extends PrimitiveArray, FFIPointer {
    @CXXValue
    MonthDayNanoIntervalType.MonthDayNanos GetValue(long i);

    @CXXValue
    MonthDayNanoIntervalType.MonthDayNanos Value(long i);

    @CXXValue
    MonthDayNanoIntervalType.MonthDayNanos GetView(long i);

    int byte_width();

    CUnsignedChar raw_values();

    static MonthDayNanoIntervalArray cast(final long __foreign_address) {
        try {
            Class<MonthDayNanoIntervalArray> clz = (Class<MonthDayNanoIntervalArray>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(MonthDayNanoIntervalArray.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static MonthDayNanoIntervalArray cast(final FFIPointer __foreign_pointer) {
        return MonthDayNanoIntervalArray.cast(__foreign_pointer.getAddress());
    }

    static Factory getFactory() {
        return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(MonthDayNanoIntervalArray.class, true));
    }

    static MonthDayNanoIntervalArray create(
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::ArrayData>") shared_ptr<ArrayData> data) {
        return MonthDayNanoIntervalArray.getFactory().create(data);
    }

    static MonthDayNanoIntervalArray create(
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::DataType>") shared_ptr<DataType> type,
            long length,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
            long null_count, long offset) {
        return MonthDayNanoIntervalArray.getFactory().create(type, length, data, null_bitmap, null_count, offset);
    }

    static MonthDayNanoIntervalArray create(long length,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
            @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
            long null_count, long offset) {
        return MonthDayNanoIntervalArray.getFactory().create(length, data, null_bitmap, null_count, offset);
    }

    @FFIFactory
    @CXXHead(
            system = "arrow/array/array_primitive.h"
    )
    interface Factory {
        MonthDayNanoIntervalArray create(
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::ArrayData>") shared_ptr<ArrayData> data);

        MonthDayNanoIntervalArray create(
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::DataType>") shared_ptr<DataType> type,
                long length,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
                long null_count, long offset);

        MonthDayNanoIntervalArray create(long length,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> data,
                @CXXReference @FFITypeAlias("const std::shared_ptr<arrow::Buffer>") shared_ptr<Buffer> null_bitmap,
                long null_count, long offset);
    }

    @FFITypeAlias("arrow::MonthDayNanoIntervalArray::TypeClass")
    @FFIGen
    @CXXHead(
            system = "arrow/array/array_primitive.h"
    )
    interface TypeClass extends CXXPointer {
        @FFIExpr("{0}")
        MonthDayNanoIntervalType get();

        @CXXValue
        @FFITypeAlias("arrow::IntervalType::type")
        IntervalType.type interval_type();

        int bit_width();

        @CXXValue
        string ToString();

        @CXXValue
        string name();

        static TypeClass cast(final long __foreign_address) {
            try {
                Class<TypeClass> clz = (Class<TypeClass>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(TypeClass.class, true));
                return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                return null;
            }
        }

        static TypeClass cast(final FFIPointer __foreign_pointer) {
            return TypeClass.cast(__foreign_pointer.getAddress());
        }

        static Factory getFactory() {
            return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(TypeClass.class, true));
        }

        static TypeClass create() {
            return TypeClass.getFactory().create();
        }

        @FFIFactory
        @CXXHead(
                system = "arrow/array/array_primitive.h"
        )
        interface Factory {
            TypeClass create();
        }

        @FFIGen
        @FFILibrary(
                value = "arrow::MonthDayNanoIntervalType",
                namespace = "arrow::MonthDayNanoIntervalType"
        )
        @CXXHead(
                system = "arrow/array/array_primitive.h"
        )
        interface Library {
            Library INSTANCE = FFITypeFactory.getLibrary(Library.class);

            CChar type_name();
        }
    }
}