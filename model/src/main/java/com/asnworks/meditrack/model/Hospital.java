/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.asnworks.meditrack.model;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Hospital extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6709292535272214939L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Hospital\",\"namespace\":\"com.asnworks.meditrack.model\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"phoneNumber\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence type;
  @Deprecated public java.lang.CharSequence name;
  @Deprecated public java.lang.CharSequence city;
  @Deprecated public java.lang.CharSequence state;
  @Deprecated public java.lang.CharSequence phoneNumber;
  @Deprecated public java.lang.CharSequence email;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Hospital() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param type The new value for type
   * @param name The new value for name
   * @param city The new value for city
   * @param state The new value for state
   * @param phoneNumber The new value for phoneNumber
   * @param email The new value for email
   */
  public Hospital(java.lang.Integer id, java.lang.CharSequence type, java.lang.CharSequence name, java.lang.CharSequence city, java.lang.CharSequence state, java.lang.CharSequence phoneNumber, java.lang.CharSequence email) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.city = city;
    this.state = state;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return type;
    case 2: return name;
    case 3: return city;
    case 4: return state;
    case 5: return phoneNumber;
    case 6: return email;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: type = (java.lang.CharSequence)value$; break;
    case 2: name = (java.lang.CharSequence)value$; break;
    case 3: city = (java.lang.CharSequence)value$; break;
    case 4: state = (java.lang.CharSequence)value$; break;
    case 5: phoneNumber = (java.lang.CharSequence)value$; break;
    case 6: email = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.CharSequence getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.CharSequence value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.CharSequence getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'city' field.
   * @return The value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }

  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'state' field.
   * @return The value of the 'state' field.
   */
  public java.lang.CharSequence getState() {
    return state;
  }

  /**
   * Sets the value of the 'state' field.
   * @param value the value to set.
   */
  public void setState(java.lang.CharSequence value) {
    this.state = value;
  }

  /**
   * Gets the value of the 'phoneNumber' field.
   * @return The value of the 'phoneNumber' field.
   */
  public java.lang.CharSequence getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the value of the 'phoneNumber' field.
   * @param value the value to set.
   */
  public void setPhoneNumber(java.lang.CharSequence value) {
    this.phoneNumber = value;
  }

  /**
   * Gets the value of the 'email' field.
   * @return The value of the 'email' field.
   */
  public java.lang.CharSequence getEmail() {
    return email;
  }

  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(java.lang.CharSequence value) {
    this.email = value;
  }

  /**
   * Creates a new Hospital RecordBuilder.
   * @return A new Hospital RecordBuilder
   */
  public static com.asnworks.meditrack.model.Hospital.Builder newBuilder() {
    return new com.asnworks.meditrack.model.Hospital.Builder();
  }

  /**
   * Creates a new Hospital RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Hospital RecordBuilder
   */
  public static com.asnworks.meditrack.model.Hospital.Builder newBuilder(com.asnworks.meditrack.model.Hospital.Builder other) {
    return new com.asnworks.meditrack.model.Hospital.Builder(other);
  }

  /**
   * Creates a new Hospital RecordBuilder by copying an existing Hospital instance.
   * @param other The existing instance to copy.
   * @return A new Hospital RecordBuilder
   */
  public static com.asnworks.meditrack.model.Hospital.Builder newBuilder(com.asnworks.meditrack.model.Hospital other) {
    return new com.asnworks.meditrack.model.Hospital.Builder(other);
  }

  /**
   * RecordBuilder for Hospital instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Hospital>
    implements org.apache.avro.data.RecordBuilder<Hospital> {

    private int id;
    private java.lang.CharSequence type;
    private java.lang.CharSequence name;
    private java.lang.CharSequence city;
    private java.lang.CharSequence state;
    private java.lang.CharSequence phoneNumber;
    private java.lang.CharSequence email;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.asnworks.meditrack.model.Hospital.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.city)) {
        this.city = data().deepCopy(fields()[3].schema(), other.city);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.state)) {
        this.state = data().deepCopy(fields()[4].schema(), other.state);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.phoneNumber)) {
        this.phoneNumber = data().deepCopy(fields()[5].schema(), other.phoneNumber);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.email)) {
        this.email = data().deepCopy(fields()[6].schema(), other.email);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Hospital instance
     * @param other The existing instance to copy.
     */
    private Builder(com.asnworks.meditrack.model.Hospital other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.city)) {
        this.city = data().deepCopy(fields()[3].schema(), other.city);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.state)) {
        this.state = data().deepCopy(fields()[4].schema(), other.state);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.phoneNumber)) {
        this.phoneNumber = data().deepCopy(fields()[5].schema(), other.phoneNumber);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.email)) {
        this.email = data().deepCopy(fields()[6].schema(), other.email);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.CharSequence getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setName(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearName() {
      name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'city' field.
      * @return The value.
      */
    public java.lang.CharSequence getCity() {
      return city;
    }

    /**
      * Sets the value of the 'city' field.
      * @param value The value of 'city'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.city = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearCity() {
      city = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'state' field.
      * @return The value.
      */
    public java.lang.CharSequence getState() {
      return state;
    }

    /**
      * Sets the value of the 'state' field.
      * @param value The value of 'state'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setState(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.state = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'state' field has been set.
      * @return True if the 'state' field has been set, false otherwise.
      */
    public boolean hasState() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'state' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearState() {
      state = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'phoneNumber' field.
      * @return The value.
      */
    public java.lang.CharSequence getPhoneNumber() {
      return phoneNumber;
    }

    /**
      * Sets the value of the 'phoneNumber' field.
      * @param value The value of 'phoneNumber'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setPhoneNumber(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.phoneNumber = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'phoneNumber' field has been set.
      * @return True if the 'phoneNumber' field has been set, false otherwise.
      */
    public boolean hasPhoneNumber() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'phoneNumber' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearPhoneNumber() {
      phoneNumber = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'email' field.
      * @return The value.
      */
    public java.lang.CharSequence getEmail() {
      return email;
    }

    /**
      * Sets the value of the 'email' field.
      * @param value The value of 'email'.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder setEmail(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.email = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'email' field has been set.
      * @return True if the 'email' field has been set, false otherwise.
      */
    public boolean hasEmail() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'email' field.
      * @return This builder.
      */
    public com.asnworks.meditrack.model.Hospital.Builder clearEmail() {
      email = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public Hospital build() {
      try {
        Hospital record = new Hospital();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.name = fieldSetFlags()[2] ? this.name : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.city = fieldSetFlags()[3] ? this.city : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.state = fieldSetFlags()[4] ? this.state : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.phoneNumber = fieldSetFlags()[5] ? this.phoneNumber : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.email = fieldSetFlags()[6] ? this.email : (java.lang.CharSequence) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
