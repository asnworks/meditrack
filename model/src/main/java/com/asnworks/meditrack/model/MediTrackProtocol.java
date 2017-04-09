/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.asnworks.meditrack.model;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface MediTrackProtocol {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"MediTrackProtocol\",\"namespace\":\"com.asnworks.meditrack.model\",\"types\":[{\"type\":\"record\",\"name\":\"Hospital\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"phoneNumber\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"Patient\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"firstName\",\"type\":\"string\"},{\"name\":\"lastName\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"},{\"name\":\"gender\",\"type\":\"string\"},{\"name\":\"address\",\"type\":\"string\"},{\"name\":\"phoneNumber\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"Doctor\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"medicalRegistrationNumber\",\"type\":\"string\"},{\"name\":\"firstName\",\"type\":\"string\"},{\"name\":\"lastName\",\"type\":\"string\"},{\"name\":\"gender\",\"type\":\"string\"},{\"name\":\"specialisation\",\"type\":\"string\"},{\"name\":\"address\",\"type\":\"string\"},{\"name\":\"phoneNumber\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"}]}],\"messages\":{}}");

  @SuppressWarnings("all")
  public interface Callback extends MediTrackProtocol {
    public static final org.apache.avro.Protocol PROTOCOL = com.asnworks.meditrack.model.MediTrackProtocol.PROTOCOL;
  }
}