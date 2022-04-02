// Code generated by alibaba/fastFFI. DO NOT EDIT.
//
package io.v6d.vineyard;

import com.alibaba.fastffi.CXXHead;
import com.alibaba.fastffi.CXXPointer;
import com.alibaba.fastffi.CXXReference;
import com.alibaba.fastffi.CXXValue;
import com.alibaba.fastffi.FFIExpr;
import com.alibaba.fastffi.FFIFactory;
import com.alibaba.fastffi.FFIGen;
import com.alibaba.fastffi.FFIPointer;
import com.alibaba.fastffi.FFITypeAlias;
import com.alibaba.fastffi.FFITypeFactory;
import io.v6d.std.CUnsignedInt;
import io.v6d.std.shared_ptr;
import io.v6d.vineyard.impl.numericarraybuilder.ArrayTypeUnsignedInt;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.IllegalAccessException;
import java.lang.InstantiationException;
import java.lang.Long;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

@FFITypeAlias("vineyard::UInt32Builder")
@FFIGen
@CXXHead("basic/ds/arrow.h")
public interface UInt32Builder extends CXXPointer {
    @FFIExpr("{0}")
    NumericArrayBuilder<CUnsignedInt> get();

    @CXXValue
    @FFITypeAlias("std::shared_ptr<vineyard::NumericArrayBuilder<unsigned int>::ArrayType>")
    shared_ptr<ArrayTypeUnsignedInt> GetArray();

    @CXXValue
    Status Build(@CXXReference Client client);

    static UInt32Builder cast(final long __foreign_address) {
        try {
            Class<UInt32Builder> clz = (Class<UInt32Builder>) FFITypeFactory.getType(FFITypeFactory.getFFITypeName(UInt32Builder.class, true));
            return clz.getConstructor(Long.TYPE).newInstance(__foreign_address);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    static UInt32Builder cast(final FFIPointer __foreign_pointer) {
        return UInt32Builder.cast(__foreign_pointer.getAddress());
    }

    static Factory getFactory() {
        return FFITypeFactory.getFactory(FFITypeFactory.getFFITypeName(UInt32Builder.class, true));
    }

    static UInt32Builder create(@CXXReference Client client,
            @CXXValue @FFITypeAlias("std::shared_ptr<vineyard::NumericArrayBuilder<unsigned int>::ArrayType>") shared_ptr<ArrayTypeUnsignedInt> array) {
        return UInt32Builder.getFactory().create(client, array);
    }

    @FFIFactory
    @CXXHead("basic/ds/arrow.h")
    interface Factory {
        UInt32Builder create(@CXXReference Client client,
                @CXXValue @FFITypeAlias("std::shared_ptr<vineyard::NumericArrayBuilder<unsigned int>::ArrayType>") shared_ptr<ArrayTypeUnsignedInt> array);
    }
}