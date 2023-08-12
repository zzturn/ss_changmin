package com.changmin.cm_backend.component.datejson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * @author renqiang
 * @since 2021/9/22
 */
public class UTCDateStdDeserializer extends StdDeserializer<Date> {

  public UTCDateStdDeserializer() {
    this(null);
  }

  public UTCDateStdDeserializer(Class<Date> vc) {
    super(vc);
  }

  @Override
  public Date deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return Date.from(Instant.ofEpochSecond(p.getLongValue()));
  }
}
