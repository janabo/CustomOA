package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JbxxRealmProxy extends com.dk.mp.core.entity.Jbxx
    implements RealmObjectProxy, JbxxRealmProxyInterface {

    static final class JbxxColumnInfo extends ColumnInfo
        implements Cloneable {

        public long prikeyIndex;
        public long idIndex;
        public long nameIndex;
        public long departmentidIndex;
        public long departmentnameIndex;
        public long phonesIndex;

        JbxxColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.prikeyIndex = getValidColumnIndex(path, table, "Jbxx", "prikey");
            indicesMap.put("prikey", this.prikeyIndex);
            this.idIndex = getValidColumnIndex(path, table, "Jbxx", "id");
            indicesMap.put("id", this.idIndex);
            this.nameIndex = getValidColumnIndex(path, table, "Jbxx", "name");
            indicesMap.put("name", this.nameIndex);
            this.departmentidIndex = getValidColumnIndex(path, table, "Jbxx", "departmentid");
            indicesMap.put("departmentid", this.departmentidIndex);
            this.departmentnameIndex = getValidColumnIndex(path, table, "Jbxx", "departmentname");
            indicesMap.put("departmentname", this.departmentnameIndex);
            this.phonesIndex = getValidColumnIndex(path, table, "Jbxx", "phones");
            indicesMap.put("phones", this.phonesIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final JbxxColumnInfo otherInfo = (JbxxColumnInfo) other;
            this.prikeyIndex = otherInfo.prikeyIndex;
            this.idIndex = otherInfo.idIndex;
            this.nameIndex = otherInfo.nameIndex;
            this.departmentidIndex = otherInfo.departmentidIndex;
            this.departmentnameIndex = otherInfo.departmentnameIndex;
            this.phonesIndex = otherInfo.phonesIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final JbxxColumnInfo clone() {
            return (JbxxColumnInfo) super.clone();
        }

    }
    private JbxxColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("prikey");
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("departmentid");
        fieldNames.add("departmentname");
        fieldNames.add("phones");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    JbxxRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (JbxxColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.dk.mp.core.entity.Jbxx.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$prikey() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.prikeyIndex);
    }

    public void realmSet$prikey(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'prikey' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.idIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$departmentid() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.departmentidIndex);
    }

    public void realmSet$departmentid(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.departmentidIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.departmentidIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.departmentidIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.departmentidIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$departmentname() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.departmentnameIndex);
    }

    public void realmSet$departmentname(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.departmentnameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.departmentnameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.departmentnameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.departmentnameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$phones() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.phonesIndex);
    }

    public void realmSet$phones(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.phonesIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.phonesIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.phonesIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.phonesIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Jbxx")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Jbxx");
            realmObjectSchema.add(new Property("prikey", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("departmentid", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("departmentname", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("phones", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Jbxx");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Jbxx")) {
            Table table = sharedRealm.getTable("class_Jbxx");
            table.addColumn(RealmFieldType.STRING, "prikey", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "departmentid", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "departmentname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "phones", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("prikey"));
            table.setPrimaryKey("prikey");
            return table;
        }
        return sharedRealm.getTable("class_Jbxx");
    }

    public static JbxxColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Jbxx")) {
            Table table = sharedRealm.getTable("class_Jbxx");
            final long columnCount = table.getColumnCount();
            if (columnCount != 6) {
                if (columnCount < 6) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 6; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final JbxxColumnInfo columnInfo = new JbxxColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("prikey")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'prikey' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("prikey") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'prikey' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.prikeyIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'prikey' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("prikey")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'prikey' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("prikey"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'prikey' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' is required. Either set @Required to field 'id' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("departmentid")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'departmentid' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("departmentid") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'departmentid' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.departmentidIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'departmentid' is required. Either set @Required to field 'departmentid' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("departmentname")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'departmentname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("departmentname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'departmentname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.departmentnameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'departmentname' is required. Either set @Required to field 'departmentname' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("phones")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'phones' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("phones") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'phones' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.phonesIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'phones' is required. Either set @Required to field 'phones' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Jbxx' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Jbxx";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.dk.mp.core.entity.Jbxx createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.dk.mp.core.entity.Jbxx obj = null;
        if (update) {
            Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("prikey")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("prikey"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class), false, Collections.<String> emptyList());
                    obj = new io.realm.JbxxRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("prikey")) {
                if (json.isNull("prikey")) {
                    obj = (io.realm.JbxxRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.Jbxx.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.JbxxRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.Jbxx.class, json.getString("prikey"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'prikey'.");
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                ((JbxxRealmProxyInterface) obj).realmSet$id(null);
            } else {
                ((JbxxRealmProxyInterface) obj).realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((JbxxRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((JbxxRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("departmentid")) {
            if (json.isNull("departmentid")) {
                ((JbxxRealmProxyInterface) obj).realmSet$departmentid(null);
            } else {
                ((JbxxRealmProxyInterface) obj).realmSet$departmentid((String) json.getString("departmentid"));
            }
        }
        if (json.has("departmentname")) {
            if (json.isNull("departmentname")) {
                ((JbxxRealmProxyInterface) obj).realmSet$departmentname(null);
            } else {
                ((JbxxRealmProxyInterface) obj).realmSet$departmentname((String) json.getString("departmentname"));
            }
        }
        if (json.has("phones")) {
            if (json.isNull("phones")) {
                ((JbxxRealmProxyInterface) obj).realmSet$phones(null);
            } else {
                ((JbxxRealmProxyInterface) obj).realmSet$phones((String) json.getString("phones"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.dk.mp.core.entity.Jbxx createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.dk.mp.core.entity.Jbxx obj = new com.dk.mp.core.entity.Jbxx();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("prikey")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$prikey(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$prikey((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("departmentid")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$departmentid(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$departmentid((String) reader.nextString());
                }
            } else if (name.equals("departmentname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$departmentname(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$departmentname((String) reader.nextString());
                }
            } else if (name.equals("phones")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((JbxxRealmProxyInterface) obj).realmSet$phones(null);
                } else {
                    ((JbxxRealmProxyInterface) obj).realmSet$phones((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'prikey'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.dk.mp.core.entity.Jbxx copyOrUpdate(Realm realm, com.dk.mp.core.entity.Jbxx object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.Jbxx) cachedRealmObject;
        } else {
            com.dk.mp.core.entity.Jbxx realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((JbxxRealmProxyInterface) object).realmGet$prikey();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.JbxxRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.dk.mp.core.entity.Jbxx copy(Realm realm, com.dk.mp.core.entity.Jbxx newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.Jbxx) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.dk.mp.core.entity.Jbxx realmObject = realm.createObjectInternal(com.dk.mp.core.entity.Jbxx.class, ((JbxxRealmProxyInterface) newObject).realmGet$prikey(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((JbxxRealmProxyInterface) realmObject).realmSet$id(((JbxxRealmProxyInterface) newObject).realmGet$id());
            ((JbxxRealmProxyInterface) realmObject).realmSet$name(((JbxxRealmProxyInterface) newObject).realmGet$name());
            ((JbxxRealmProxyInterface) realmObject).realmSet$departmentid(((JbxxRealmProxyInterface) newObject).realmGet$departmentid());
            ((JbxxRealmProxyInterface) realmObject).realmSet$departmentname(((JbxxRealmProxyInterface) newObject).realmGet$departmentname());
            ((JbxxRealmProxyInterface) realmObject).realmSet$phones(((JbxxRealmProxyInterface) newObject).realmGet$phones());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.dk.mp.core.entity.Jbxx object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
        long tableNativePtr = table.getNativeTablePointer();
        JbxxColumnInfo columnInfo = (JbxxColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((JbxxRealmProxyInterface) object).realmGet$prikey();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$id = ((JbxxRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$name = ((JbxxRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$departmentid = ((JbxxRealmProxyInterface)object).realmGet$departmentid();
        if (realmGet$departmentid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.departmentidIndex, rowIndex, realmGet$departmentid, false);
        }
        String realmGet$departmentname = ((JbxxRealmProxyInterface)object).realmGet$departmentname();
        if (realmGet$departmentname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, realmGet$departmentname, false);
        }
        String realmGet$phones = ((JbxxRealmProxyInterface)object).realmGet$phones();
        if (realmGet$phones != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phonesIndex, rowIndex, realmGet$phones, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
        long tableNativePtr = table.getNativeTablePointer();
        JbxxColumnInfo columnInfo = (JbxxColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.Jbxx object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.Jbxx) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((JbxxRealmProxyInterface) object).realmGet$prikey();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$id = ((JbxxRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                }
                String realmGet$name = ((JbxxRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                String realmGet$departmentid = ((JbxxRealmProxyInterface)object).realmGet$departmentid();
                if (realmGet$departmentid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.departmentidIndex, rowIndex, realmGet$departmentid, false);
                }
                String realmGet$departmentname = ((JbxxRealmProxyInterface)object).realmGet$departmentname();
                if (realmGet$departmentname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, realmGet$departmentname, false);
                }
                String realmGet$phones = ((JbxxRealmProxyInterface)object).realmGet$phones();
                if (realmGet$phones != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phonesIndex, rowIndex, realmGet$phones, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.dk.mp.core.entity.Jbxx object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
        long tableNativePtr = table.getNativeTablePointer();
        JbxxColumnInfo columnInfo = (JbxxColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((JbxxRealmProxyInterface) object).realmGet$prikey();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$id = ((JbxxRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$name = ((JbxxRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$departmentid = ((JbxxRealmProxyInterface)object).realmGet$departmentid();
        if (realmGet$departmentid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.departmentidIndex, rowIndex, realmGet$departmentid, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.departmentidIndex, rowIndex, false);
        }
        String realmGet$departmentname = ((JbxxRealmProxyInterface)object).realmGet$departmentname();
        if (realmGet$departmentname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, realmGet$departmentname, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, false);
        }
        String realmGet$phones = ((JbxxRealmProxyInterface)object).realmGet$phones();
        if (realmGet$phones != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phonesIndex, rowIndex, realmGet$phones, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.phonesIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.Jbxx.class);
        long tableNativePtr = table.getNativeTablePointer();
        JbxxColumnInfo columnInfo = (JbxxColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Jbxx.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.Jbxx object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.Jbxx) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((JbxxRealmProxyInterface) object).realmGet$prikey();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$id = ((JbxxRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                }
                String realmGet$name = ((JbxxRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                String realmGet$departmentid = ((JbxxRealmProxyInterface)object).realmGet$departmentid();
                if (realmGet$departmentid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.departmentidIndex, rowIndex, realmGet$departmentid, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.departmentidIndex, rowIndex, false);
                }
                String realmGet$departmentname = ((JbxxRealmProxyInterface)object).realmGet$departmentname();
                if (realmGet$departmentname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, realmGet$departmentname, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.departmentnameIndex, rowIndex, false);
                }
                String realmGet$phones = ((JbxxRealmProxyInterface)object).realmGet$phones();
                if (realmGet$phones != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.phonesIndex, rowIndex, realmGet$phones, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.phonesIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.dk.mp.core.entity.Jbxx createDetachedCopy(com.dk.mp.core.entity.Jbxx realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.dk.mp.core.entity.Jbxx unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.dk.mp.core.entity.Jbxx)cachedObject.object;
            } else {
                unmanagedObject = (com.dk.mp.core.entity.Jbxx)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.dk.mp.core.entity.Jbxx();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$prikey(((JbxxRealmProxyInterface) realmObject).realmGet$prikey());
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$id(((JbxxRealmProxyInterface) realmObject).realmGet$id());
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$name(((JbxxRealmProxyInterface) realmObject).realmGet$name());
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$departmentid(((JbxxRealmProxyInterface) realmObject).realmGet$departmentid());
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$departmentname(((JbxxRealmProxyInterface) realmObject).realmGet$departmentname());
        ((JbxxRealmProxyInterface) unmanagedObject).realmSet$phones(((JbxxRealmProxyInterface) realmObject).realmGet$phones());
        return unmanagedObject;
    }

    static com.dk.mp.core.entity.Jbxx update(Realm realm, com.dk.mp.core.entity.Jbxx realmObject, com.dk.mp.core.entity.Jbxx newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((JbxxRealmProxyInterface) realmObject).realmSet$id(((JbxxRealmProxyInterface) newObject).realmGet$id());
        ((JbxxRealmProxyInterface) realmObject).realmSet$name(((JbxxRealmProxyInterface) newObject).realmGet$name());
        ((JbxxRealmProxyInterface) realmObject).realmSet$departmentid(((JbxxRealmProxyInterface) newObject).realmGet$departmentid());
        ((JbxxRealmProxyInterface) realmObject).realmSet$departmentname(((JbxxRealmProxyInterface) newObject).realmGet$departmentname());
        ((JbxxRealmProxyInterface) realmObject).realmSet$phones(((JbxxRealmProxyInterface) newObject).realmGet$phones());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Jbxx = [");
        stringBuilder.append("{prikey:");
        stringBuilder.append(realmGet$prikey() != null ? realmGet$prikey() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{departmentid:");
        stringBuilder.append(realmGet$departmentid() != null ? realmGet$departmentid() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{departmentname:");
        stringBuilder.append(realmGet$departmentname() != null ? realmGet$departmentname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{phones:");
        stringBuilder.append(realmGet$phones() != null ? realmGet$phones() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JbxxRealmProxy aJbxx = (JbxxRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aJbxx.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aJbxx.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aJbxx.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
