package es.angelillo15.core.chat.chat;

import com.google.gson.*;
import es.angelillo15.core.chat.api.chat.KeybindComponent;

import java.lang.reflect.Type;

public class KeybindComponentSerializer extends BaseComponentSerializer implements JsonSerializer<KeybindComponent>, JsonDeserializer<KeybindComponent>
{

    @Override
    public KeybindComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject object = json.getAsJsonObject();
        if ( !object.has( "keybind" ) )
        {
            throw new JsonParseException( "Could not parse JSON: missing 'keybind' property" );
        }
        KeybindComponent component = new KeybindComponent();
        deserialize( object, component, context );
        component.setKeybind( object.get( "keybind" ).getAsString() );
        return component;
    }

    @Override
    public JsonElement serialize(KeybindComponent src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject object = new JsonObject();
        serialize( object, src, context );
        object.addProperty( "keybind", src.getKeybind() );
        return object;
    }
}
