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

public class RcapRealmProxy extends com.dk.mp.core.entity.Rcap
    implements RealmObjectProxy, RcapRealmProxyInterface {

    static final class RcapColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long titleIndex;
        public long contentIndex;
        public long time_startIndex;
        public long time_endIndex;
        public long locationIndex;
        public long stimeIndex;

        RcapColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(7);
            this.idIndex = getValidColumnIndex(path, table, "Rcap", "id");
            indicesMap.put("id", this.idIndex);
            this.titleIndex = getValidColumnIndex(path, table, "Rcap", "title");
            indicesMap.put("title", this.titleIndex);
            this.contentIndex = getValidColumnIndex(path, table, "Rcap", "content");
            indicesMap.put("content", this.contentIndex);
            this.time_startIndex = getValidColumnIndex(path, table, "Rcap", "time_start");
            indicesMap.put("time_start", this.time_startIndex);
            this.time_endIndex = getValidColumnIndex(path, table, "Rcap", "time_end");
            indicesMap.put("time_end", this.time_endIndex);
            this.locationIndex = getValidColumnIndex(path, table, "Rcap", "location");
            indicesMap.put("location", this.locationIndex);
            this.stimeIndex = getValidColumnIndex(path, table, "Rcap", "stime");
            indicesMap.put("stime", this.stimeIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final RcapColumnInfo otherInfo = (RcapColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.contentIndex = otherInfo.contentIndex;
            this.time_startIndex = otherInfo.time_startIndex;
            this.time_endIndex = otherInfo.time_endIndex;
            this.locationIndex = otherInfo.locationIndex;
            this.stimeIndex = otherInfo.stimeIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final RcapColumnInfo clone() {
            return (RcapColumnInfo) super.clone();
        }

    }
    private RcapColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("title");
        fieldNames.add("content");
        fieldNames.add("time_start");
        fieldNames.add("time_end");
        fieldNames.add("location");
        fieldNames.add("stime");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RcapRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (RcapColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.dk.mp.core.entity.Rcap.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public String realmGet$title() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    public void realmSet$title(String value) {
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
                row.getTable().setNull(columnInfo.titleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$content() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.contentIndex);
    }

    public void realmSet$content(String value) {
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
                row.getTable().setNull(columnInfo.contentIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.contentIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.contentIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.contentIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$time_start() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.time_startIndex);
    }

    public void realmSet$time_start(String value) {
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
                row.getTable().setNull(columnInfo.time_startIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.time_startIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.time_startIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.time_startIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$time_end() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.time_endIndex);
    }

    public void realmSet$time_end(String value) {
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
                row.getTable().setNull(columnInfo.time_endIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.time_endIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.time_endIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.time_endIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$location() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.locationIndex);
    }

    public void realmSet$location(String value) {
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
                row.getTable().setNull(columnInfo.locationIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.locationIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.locationIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.locationIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$stime() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.stimeIndex);
    }

    public void realmSet$stime(String value) {
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
                row.getTable().setNull(columnInfo.stimeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.stimeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.stimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.stimeIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Rcap")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Rcap");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("content", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("time_start", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("time_end", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("location", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("stime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Rcap");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Rcap")) {
            Table table = sharedRealm.getTable("class_Rcap");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "time_start", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "time_end", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "location", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "stime", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Rcap");
    }

    public static RcapColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Rcap")) {
            Table table = sharedRealm.getTable("class_Rcap");
            final long columnCount = table.getColumnCount();
            if (columnCount != 7) {
                if (columnCount < 7) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 7 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 7 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 7 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 7; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final RcapColumnInfo columnInfo = new RcapColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("content")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'content' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("content") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'content' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.contentIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'content' is required. Either set @Required to field 'content' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("time_start")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'time_start' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("time_start") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'time_start' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.time_startIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'time_start' is required. Either set @Required to field 'time_start' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("time_end")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'time_end' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("time_end") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'time_end' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.time_endIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'time_end' is required. Either set @Required to field 'time_end' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("location")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'location' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("location") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'location' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.locationIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'location' is required. Either set @Required to field 'location' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("stime")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'stime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("stime") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'stime' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.stimeIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'stime' is required. Either set @Required to field 'stime' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Rcap' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Rcap";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.dk.mp.core.entity.Rcap createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.dk.mp.core.entity.Rcap obj = null;
        if (update) {
            Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class), false, Collections.<String> emptyList());
                    obj = new io.realm.RcapRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.RcapRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.Rcap.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.RcapRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.Rcap.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((RcapRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                ((RcapRealmProxyInterface) obj).realmSet$content(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$content((String) json.getString("content"));
            }
        }
        if (json.has("time_start")) {
            if (json.isNull("time_start")) {
                ((RcapRealmProxyInterface) obj).realmSet$time_start(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$time_start((String) json.getString("time_start"));
            }
        }
        if (json.has("time_end")) {
            if (json.isNull("time_end")) {
                ((RcapRealmProxyInterface) obj).realmSet$time_end(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$time_end((String) json.getString("time_end"));
            }
        }
        if (json.has("location")) {
            if (json.isNull("location")) {
                ((RcapRealmProxyInterface) obj).realmSet$location(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$location((String) json.getString("location"));
            }
        }
        if (json.has("stime")) {
            if (json.isNull("stime")) {
                ((RcapRealmProxyInterface) obj).realmSet$stime(null);
            } else {
                ((RcapRealmProxyInterface) obj).realmSet$stime((String) json.getString("stime"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.dk.mp.core.entity.Rcap createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.dk.mp.core.entity.Rcap obj = new com.dk.mp.core.entity.Rcap();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$content(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$content((String) reader.nextString());
                }
            } else if (name.equals("time_start")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$time_start(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$time_start((String) reader.nextString());
                }
            } else if (name.equals("time_end")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$time_end(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$time_end((String) reader.nextString());
                }
            } else if (name.equals("location")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$location(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$location((String) reader.nextString());
                }
            } else if (name.equals("stime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapRealmProxyInterface) obj).realmSet$stime(null);
                } else {
                    ((RcapRealmProxyInterface) obj).realmSet$stime((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.dk.mp.core.entity.Rcap copyOrUpdate(Realm realm, com.dk.mp.core.entity.Rcap object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.Rcap) cachedRealmObject;
        } else {
            com.dk.mp.core.entity.Rcap realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((RcapRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.RcapRealmProxy();
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

    public static com.dk.mp.core.entity.Rcap copy(Realm realm, com.dk.mp.core.entity.Rcap newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.Rcap) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.dk.mp.core.entity.Rcap realmObject = realm.createObjectInternal(com.dk.mp.core.entity.Rcap.class, ((RcapRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((RcapRealmProxyInterface) realmObject).realmSet$title(((RcapRealmProxyInterface) newObject).realmGet$title());
            ((RcapRealmProxyInterface) realmObject).realmSet$content(((RcapRealmProxyInterface) newObject).realmGet$content());
            ((RcapRealmProxyInterface) realmObject).realmSet$time_start(((RcapRealmProxyInterface) newObject).realmGet$time_start());
            ((RcapRealmProxyInterface) realmObject).realmSet$time_end(((RcapRealmProxyInterface) newObject).realmGet$time_end());
            ((RcapRealmProxyInterface) realmObject).realmSet$location(((RcapRealmProxyInterface) newObject).realmGet$location());
            ((RcapRealmProxyInterface) realmObject).realmSet$stime(((RcapRealmProxyInterface) newObject).realmGet$stime());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.dk.mp.core.entity.Rcap object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapColumnInfo columnInfo = (RcapColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RcapRealmProxyInterface) object).realmGet$id();
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
        String realmGet$title = ((RcapRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$content = ((RcapRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        }
        String realmGet$time_start = ((RcapRealmProxyInterface)object).realmGet$time_start();
        if (realmGet$time_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
        }
        String realmGet$time_end = ((RcapRealmProxyInterface)object).realmGet$time_end();
        if (realmGet$time_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
        }
        String realmGet$location = ((RcapRealmProxyInterface)object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
        }
        String realmGet$stime = ((RcapRealmProxyInterface)object).realmGet$stime();
        if (realmGet$stime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapColumnInfo columnInfo = (RcapColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.Rcap object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.Rcap) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RcapRealmProxyInterface) object).realmGet$id();
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
                String realmGet$title = ((RcapRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }
                String realmGet$content = ((RcapRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                }
                String realmGet$time_start = ((RcapRealmProxyInterface)object).realmGet$time_start();
                if (realmGet$time_start != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
                }
                String realmGet$time_end = ((RcapRealmProxyInterface)object).realmGet$time_end();
                if (realmGet$time_end != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
                }
                String realmGet$location = ((RcapRealmProxyInterface)object).realmGet$location();
                if (realmGet$location != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
                }
                String realmGet$stime = ((RcapRealmProxyInterface)object).realmGet$stime();
                if (realmGet$stime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.dk.mp.core.entity.Rcap object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapColumnInfo columnInfo = (RcapColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RcapRealmProxyInterface) object).realmGet$id();
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
        String realmGet$title = ((RcapRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$content = ((RcapRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
        }
        String realmGet$time_start = ((RcapRealmProxyInterface)object).realmGet$time_start();
        if (realmGet$time_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.time_startIndex, rowIndex, false);
        }
        String realmGet$time_end = ((RcapRealmProxyInterface)object).realmGet$time_end();
        if (realmGet$time_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.time_endIndex, rowIndex, false);
        }
        String realmGet$location = ((RcapRealmProxyInterface)object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.locationIndex, rowIndex, false);
        }
        String realmGet$stime = ((RcapRealmProxyInterface)object).realmGet$stime();
        if (realmGet$stime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.stimeIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.Rcap.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapColumnInfo columnInfo = (RcapColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.Rcap.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.Rcap object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.Rcap) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RcapRealmProxyInterface) object).realmGet$id();
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
                String realmGet$title = ((RcapRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }
                String realmGet$content = ((RcapRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
                }
                String realmGet$time_start = ((RcapRealmProxyInterface)object).realmGet$time_start();
                if (realmGet$time_start != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.time_startIndex, rowIndex, false);
                }
                String realmGet$time_end = ((RcapRealmProxyInterface)object).realmGet$time_end();
                if (realmGet$time_end != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.time_endIndex, rowIndex, false);
                }
                String realmGet$location = ((RcapRealmProxyInterface)object).realmGet$location();
                if (realmGet$location != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.locationIndex, rowIndex, false);
                }
                String realmGet$stime = ((RcapRealmProxyInterface)object).realmGet$stime();
                if (realmGet$stime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.stimeIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.dk.mp.core.entity.Rcap createDetachedCopy(com.dk.mp.core.entity.Rcap realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.dk.mp.core.entity.Rcap unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.dk.mp.core.entity.Rcap)cachedObject.object;
            } else {
                unmanagedObject = (com.dk.mp.core.entity.Rcap)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.dk.mp.core.entity.Rcap();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$id(((RcapRealmProxyInterface) realmObject).realmGet$id());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$title(((RcapRealmProxyInterface) realmObject).realmGet$title());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$content(((RcapRealmProxyInterface) realmObject).realmGet$content());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$time_start(((RcapRealmProxyInterface) realmObject).realmGet$time_start());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$time_end(((RcapRealmProxyInterface) realmObject).realmGet$time_end());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$location(((RcapRealmProxyInterface) realmObject).realmGet$location());
        ((RcapRealmProxyInterface) unmanagedObject).realmSet$stime(((RcapRealmProxyInterface) realmObject).realmGet$stime());
        return unmanagedObject;
    }

    static com.dk.mp.core.entity.Rcap update(Realm realm, com.dk.mp.core.entity.Rcap realmObject, com.dk.mp.core.entity.Rcap newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((RcapRealmProxyInterface) realmObject).realmSet$title(((RcapRealmProxyInterface) newObject).realmGet$title());
        ((RcapRealmProxyInterface) realmObject).realmSet$content(((RcapRealmProxyInterface) newObject).realmGet$content());
        ((RcapRealmProxyInterface) realmObject).realmSet$time_start(((RcapRealmProxyInterface) newObject).realmGet$time_start());
        ((RcapRealmProxyInterface) realmObject).realmSet$time_end(((RcapRealmProxyInterface) newObject).realmGet$time_end());
        ((RcapRealmProxyInterface) realmObject).realmSet$location(((RcapRealmProxyInterface) newObject).realmGet$location());
        ((RcapRealmProxyInterface) realmObject).realmSet$stime(((RcapRealmProxyInterface) newObject).realmGet$stime());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Rcap = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(realmGet$content() != null ? realmGet$content() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time_start:");
        stringBuilder.append(realmGet$time_start() != null ? realmGet$time_start() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time_end:");
        stringBuilder.append(realmGet$time_end() != null ? realmGet$time_end() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{location:");
        stringBuilder.append(realmGet$location() != null ? realmGet$location() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stime:");
        stringBuilder.append(realmGet$stime() != null ? realmGet$stime() : "null");
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
        RcapRealmProxy aRcap = (RcapRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aRcap.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRcap.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aRcap.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
