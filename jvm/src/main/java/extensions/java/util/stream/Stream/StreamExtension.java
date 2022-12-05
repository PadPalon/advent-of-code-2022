package extensions.java.util.stream.Stream;

import java.util.stream.Stream;

import com.google.common.collect.Streams;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

@Extension
public class StreamExtension {
    public static <T, R> Stream<R> mapWithIndex(@This Stream<T> source, Streams.FunctionWithIndex<? super T, R> function) {
        return Streams.mapWithIndex(source, function);
    }
}
