package com.changmin.cm_backend.component.datejson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author renqiang
 * @since 2021/9/22
 */
public class UTCDateStdSerializer extends StdSerializer<Date> {
  public UTCDateStdSerializer() {
    this(null);
  }

  public UTCDateStdSerializer(Class<Date> vc) {
    super(vc);
  }

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    if (Objects.nonNull(value)) gen.writeNumber(value.toInstant().getEpochSecond());
  }
}
