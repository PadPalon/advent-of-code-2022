package ch.neukom.advent2022.day13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import static java.util.stream.Collectors.*;

public class Util {

    public static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(
            ListPart.class,
            (JsonDeserializer<ListPart>) (json, type, context) -> parseListPart(json)
        ).create();

    private Util() {
    }

    public static ListPart parsePart(String line) {
        return GSON.fromJson(line, ListPart.class);
    }

    private static ListPart parseListPart(JsonElement json) {
        return json.getAsJsonArray()
            .asList()
            .stream()
            .map(part -> {
                if (part.isJsonArray()) {
                    return parseListPart(part);
                } else {
                    return new NumberPart(part.getAsInt());
                }
            })
            .collect(collectingAndThen(toList(), ListPart::new));
    }
}
