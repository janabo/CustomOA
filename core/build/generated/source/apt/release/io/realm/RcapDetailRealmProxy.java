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

public class RcapDetailRealmProxy extends com.dk.mp.core.entity.RcapDetail
    implements RealmObjectProxy, RcapDetailRealmProxyInterface {

    static final class RcapDetailColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long titleIndex;
        public long contentIndex;
        public long dateIndex;
        public long locationIndex;
        public long stimeIndex;
        public long rcidIndex;
        public long time_startIndex;
        public long time_endIndex;

        RcapDetailColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(9);
            this.idIndex = getValidColumnIndex(path, table, "RcapDetail", "id");
            indicesMap.put("id", this.idIndex);
            this.titleIndex = getValidColumnIndex(path, table, "RcapDetail", "title");
            indicesMap.put("title", this.titleIndex);
            this.contentIndex = getValidColumnIndex(path, table, "RcapDetail", "content");
            indicesMap.put("content", this.contentIndex);
            this.dateIndex = getValidColumnIndex(path, table, "RcapDetail", "date");
            indicesMap.put("date", this.dateIndex);
            this.locationIndex = getValidColumnIndex(path, table, "RcapDetail", "location");
            indicesMap.put("location", this.locationIndex);
            this.stimeIndex = getValidColumnIndex(path, table, "RcapDetail", "stime");
            indicesMap.put("stime", this.stimeIndex);
            this.rcidIndex = getValidColumnIndex(path, table, "RcapDetail", "rcid");
            indicesMap.put("rcid", this.rcidIndex);
            this.time_startIndex = getValidColumnIndex(path, table, "RcapDetail", "time_start");
            indicesMap.put("time_start", this.time_startIndex);
            this.time_endIndex = getValidColumnIndex(path, table, "RcapDetail", "time_end");
            indicesMap.put("time_end", this.time_endIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final RcapDetailColumnInfo otherInfo = (RcapDetailColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.titleIndex = otherInfo.titleIndex;
            this.contentIndex = otherInfo.contentIndex;
            this.dateIndex = otherInfo.dateIndex;
            this.locationIndex = otherInfo.locationIndex;
            this.stimeIndex = otherInfo.stimeIndex;
            this.rcidIndex = otherInfo.rcidIndex;
            this.time_startIndex = otherInfo.time_startIndex;
            this.time_endIndex = otherInfo.time_endIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final RcapDetailColumnInfo clone() {
            return (RcapDetailColumnInfo) super.clone();
        }

    }
    private RcapDetailColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("title");
        fieldNames.add("content");
        fieldNames.add("date");
        fieldNames.add("location");
        fieldNames.add("stime");
        fieldNames.add("rcid");
        fieldNames.add("time_start");
        fieldNames.add("time_end");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RcapDetailRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (RcapDetailColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.dk.mp.core.entity.RcapDetail.class, this);
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
    public String realmGet$date() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dateIndex);
    }

    public void realmSet$date(String value) {
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
                row.getTable().setNull(columnInfo.dateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dateIndex, value);
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

    @SuppressWarnings("cast")
    public String realmGet$rcid() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.rcidIndex);
    }

    public void realmSet$rcid(String value) {
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
                row.getTable().setNull(columnInfo.rcidIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.rcidIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.rcidIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.rcidIndex, value);
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

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("RcapDetail")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("RcapDetail");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("content", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("date", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("location", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("stime", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("rcid", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("time_start", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("time_end", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("RcapDetail");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_RcapDetail")) {
            Table table = sharedRealm.getTable("class_RcapDetail");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "date", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "location", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "stime", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "rcid", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "time_start", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "time_end", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_RcapDetail");
    }

    public static RcapDetailColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_RcapDetail")) {
            Table table = sharedRealm.getTable("class_RcapDetail");
            final long columnCount = table.getColumnCount();
            if (columnCount != 9) {
                if (columnCount < 9) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 9 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 9 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 9 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 9; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final RcapDetailColumnInfo columnInfo = new RcapDetailColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("date")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'date' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("date") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'date' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.dateIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'date' is required. Either set @Required to field 'date' or migrate using RealmObjectSchema.setNullable().");
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
            if (!columnTypes.containsKey("rcid")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'rcid' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("rcid") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'rcid' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.rcidIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'rcid' is required. Either set @Required to field 'rcid' or migrate using RealmObjectSchema.setNullable().");
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
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'RcapDetail' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_RcapDetail";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.dk.mp.core.entity.RcapDetail createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.dk.mp.core.entity.RcapDetail obj = null;
        if (update) {
            Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class), false, Collections.<String> emptyList());
                    obj = new io.realm.RcapDetailRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.RcapDetailRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.RcapDetail.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.RcapDetailRealmProxy) realm.createObjectInternal(com.dk.mp.core.entity.RcapDetail.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$title(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$content(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$content((String) json.getString("content"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$date(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$date((String) json.getString("date"));
            }
        }
        if (json.has("location")) {
            if (json.isNull("location")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$location(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$location((String) json.getString("location"));
            }
        }
        if (json.has("stime")) {
            if (json.isNull("stime")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$stime(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$stime((String) json.getString("stime"));
            }
        }
        if (json.has("rcid")) {
            if (json.isNull("rcid")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$rcid(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$rcid((String) json.getString("rcid"));
            }
        }
        if (json.has("time_start")) {
            if (json.isNull("time_start")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$time_start(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$time_start((String) json.getString("time_start"));
            }
        }
        if (json.has("time_end")) {
            if (json.isNull("time_end")) {
                ((RcapDetailRealmProxyInterface) obj).realmSet$time_end(null);
            } else {
                ((RcapDetailRealmProxyInterface) obj).realmSet$time_end((String) json.getString("time_end"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.dk.mp.core.entity.RcapDetail createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.dk.mp.core.entity.RcapDetail obj = new com.dk.mp.core.entity.RcapDetail();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$title(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$title((String) reader.nextString());
                }
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$content(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$content((String) reader.nextString());
                }
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$date(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$date((String) reader.nextString());
                }
            } else if (name.equals("location")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$location(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$location((String) reader.nextString());
                }
            } else if (name.equals("stime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$stime(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$stime((String) reader.nextString());
                }
            } else if (name.equals("rcid")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$rcid(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$rcid((String) reader.nextString());
                }
            } else if (name.equals("time_start")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$time_start(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$time_start((String) reader.nextString());
                }
            } else if (name.equals("time_end")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((RcapDetailRealmProxyInterface) obj).realmSet$time_end(null);
                } else {
                    ((RcapDetailRealmProxyInterface) obj).realmSet$time_end((String) reader.nextString());
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

    public static com.dk.mp.core.entity.RcapDetail copyOrUpdate(Realm realm, com.dk.mp.core.entity.RcapDetail object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.RcapDetail) cachedRealmObject;
        } else {
            com.dk.mp.core.entity.RcapDetail realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((RcapDetailRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.RcapDetailRealmProxy();
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

    public static com.dk.mp.core.entity.RcapDetail copy(Realm realm, com.dk.mp.core.entity.RcapDetail newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.dk.mp.core.entity.RcapDetail) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.dk.mp.core.entity.RcapDetail realmObject = realm.createObjectInternal(com.dk.mp.core.entity.RcapDetail.class, ((RcapDetailRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$title(((RcapDetailRealmProxyInterface) newObject).realmGet$title());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$content(((RcapDetailRealmProxyInterface) newObject).realmGet$content());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$date(((RcapDetailRealmProxyInterface) newObject).realmGet$date());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$location(((RcapDetailRealmProxyInterface) newObject).realmGet$location());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$stime(((RcapDetailRealmProxyInterface) newObject).realmGet$stime());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$rcid(((RcapDetailRealmProxyInterface) newObject).realmGet$rcid());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$time_start(((RcapDetailRealmProxyInterface) newObject).realmGet$time_start());
            ((RcapDetailRealmProxyInterface) realmObject).realmSet$time_end(((RcapDetailRealmProxyInterface) newObject).realmGet$time_end());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.dk.mp.core.entity.RcapDetail object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapDetailColumnInfo columnInfo = (RcapDetailColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RcapDetailRealmProxyInterface) object).realmGet$id();
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
        String realmGet$title = ((RcapDetailRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$content = ((RcapDetailRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        }
        String realmGet$date = ((RcapDetailRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        }
        String realmGet$location = ((RcapDetailRealmProxyInterface)object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
        }
        String realmGet$stime = ((RcapDetailRealmProxyInterface)object).realmGet$stime();
        if (realmGet$stime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
        }
        String realmGet$rcid = ((RcapDetailRealmProxyInterface)object).realmGet$rcid();
        if (realmGet$rcid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rcidIndex, rowIndex, realmGet$rcid, false);
        }
        String realmGet$time_start = ((RcapDetailRealmProxyInterface)object).realmGet$time_start();
        if (realmGet$time_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
        }
        String realmGet$time_end = ((RcapDetailRealmProxyInterface)object).realmGet$time_end();
        if (realmGet$time_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapDetailColumnInfo columnInfo = (RcapDetailColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.RcapDetail object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.RcapDetail) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RcapDetailRealmProxyInterface) object).realmGet$id();
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
                String realmGet$title = ((RcapDetailRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                }
                String realmGet$content = ((RcapDetailRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                }
                String realmGet$date = ((RcapDetailRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
                }
                String realmGet$location = ((RcapDetailRealmProxyInterface)object).realmGet$location();
                if (realmGet$location != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
                }
                String realmGet$stime = ((RcapDetailRealmProxyInterface)object).realmGet$stime();
                if (realmGet$stime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
                }
                String realmGet$rcid = ((RcapDetailRealmProxyInterface)object).realmGet$rcid();
                if (realmGet$rcid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rcidIndex, rowIndex, realmGet$rcid, false);
                }
                String realmGet$time_start = ((RcapDetailRealmProxyInterface)object).realmGet$time_start();
                if (realmGet$time_start != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
                }
                String realmGet$time_end = ((RcapDetailRealmProxyInterface)object).realmGet$time_end();
                if (realmGet$time_end != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.dk.mp.core.entity.RcapDetail object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapDetailColumnInfo columnInfo = (RcapDetailColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((RcapDetailRealmProxyInterface) object).realmGet$id();
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
        String realmGet$title = ((RcapDetailRealmProxyInterface)object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$content = ((RcapDetailRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
        }
        String realmGet$date = ((RcapDetailRealmProxyInterface)object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
        }
        String realmGet$location = ((RcapDetailRealmProxyInterface)object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.locationIndex, rowIndex, false);
        }
        String realmGet$stime = ((RcapDetailRealmProxyInterface)object).realmGet$stime();
        if (realmGet$stime != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.stimeIndex, rowIndex, false);
        }
        String realmGet$rcid = ((RcapDetailRealmProxyInterface)object).realmGet$rcid();
        if (realmGet$rcid != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rcidIndex, rowIndex, realmGet$rcid, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rcidIndex, rowIndex, false);
        }
        String realmGet$time_start = ((RcapDetailRealmProxyInterface)object).realmGet$time_start();
        if (realmGet$time_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.time_startIndex, rowIndex, false);
        }
        String realmGet$time_end = ((RcapDetailRealmProxyInterface)object).realmGet$time_end();
        if (realmGet$time_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.time_endIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.dk.mp.core.entity.RcapDetail.class);
        long tableNativePtr = table.getNativeTablePointer();
        RcapDetailColumnInfo columnInfo = (RcapDetailColumnInfo) realm.schema.getColumnInfo(com.dk.mp.core.entity.RcapDetail.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.dk.mp.core.entity.RcapDetail object = null;
        while (objects.hasNext()) {
            object = (com.dk.mp.core.entity.RcapDetail) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((RcapDetailRealmProxyInterface) object).realmGet$id();
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
                String realmGet$title = ((RcapDetailRealmProxyInterface)object).realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
                }
                String realmGet$content = ((RcapDetailRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
                }
                String realmGet$date = ((RcapDetailRealmProxyInterface)object).realmGet$date();
                if (realmGet$date != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
                }
                String realmGet$location = ((RcapDetailRealmProxyInterface)object).realmGet$location();
                if (realmGet$location != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.locationIndex, rowIndex, realmGet$location, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.locationIndex, rowIndex, false);
                }
                String realmGet$stime = ((RcapDetailRealmProxyInterface)object).realmGet$stime();
                if (realmGet$stime != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.stimeIndex, rowIndex, realmGet$stime, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.stimeIndex, rowIndex, false);
                }
                String realmGet$rcid = ((RcapDetailRealmProxyInterface)object).realmGet$rcid();
                if (realmGet$rcid != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rcidIndex, rowIndex, realmGet$rcid, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.rcidIndex, rowIndex, false);
                }
                String realmGet$time_start = ((RcapDetailRealmProxyInterface)object).realmGet$time_start();
                if (realmGet$time_start != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_startIndex, rowIndex, realmGet$time_start, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.time_startIndex, rowIndex, false);
                }
                String realmGet$time_end = ((RcapDetailRealmProxyInterface)object).realmGet$time_end();
                if (realmGet$time_end != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.time_endIndex, rowIndex, realmGet$time_end, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.time_endIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.dk.mp.core.entity.RcapDetail createDetachedCopy(com.dk.mp.core.entity.RcapDetail realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.dk.mp.core.entity.RcapDetail unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.dk.mp.core.entity.RcapDetail)cachedObject.object;
            } else {
                unmanagedObject = (com.dk.mp.core.entity.RcapDetail)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.dk.mp.core.entity.RcapDetail();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$id(((RcapDetailRealmProxyInterface) realmObject).realmGet$id());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$title(((RcapDetailRealmProxyInterface) realmObject).realmGet$title());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$content(((RcapDetailRealmProxyInterface) realmObject).realmGet$content());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$date(((RcapDetailRealmProxyInterface) realmObject).realmGet$date());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$location(((RcapDetailRealmProxyInterface) realmObject).realmGet$location());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$stime(((RcapDetailRealmProxyInterface) realmObject).realmGet$stime());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$rcid(((RcapDetailRealmProxyInterface) realmObject).realmGet$rcid());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$time_start(((RcapDetailRealmProxyInterface) realmObject).realmGet$time_start());
        ((RcapDetailRealmProxyInterface) unmanagedObject).realmSet$time_end(((RcapDetailRealmProxyInterface) realmObject).realmGet$time_end());
        return unmanagedObject;
    }

    static com.dk.mp.core.entity.RcapDetail update(Realm realm, com.dk.mp.core.entity.RcapDetail realmObject, com.dk.mp.core.entity.RcapDetail newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$title(((RcapDetailRealmProxyInterface) newObject).realmGet$title());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$content(((RcapDetailRealmProxyInterface) newObject).realmGet$content());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$date(((RcapDetailRealmProxyInterface) newObject).realmGet$date());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$location(((RcapDetailRealmProxyInterface) newObject).realmGet$location());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$stime(((RcapDetailRealmProxyInterface) newObject).realmGet$stime());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$rcid(((RcapDetailRealmProxyInterface) newObject).realmGet$rcid());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$time_start(((RcapDetailRealmProxyInterface) newObject).realmGet$time_start());
        ((RcapDetailRealmProxyInterface) realmObject).realmSet$time_end(((RcapDetailRealmProxyInterface) newObject).realmGet$time_end());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RcapDetail = [");
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
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date() != null ? realmGet$date() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{location:");
        stringBuilder.append(realmGet$location() != null ? realmGet$location() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stime:");
        stringBuilder.append(realmGet$stime() != null ? realmGet$stime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rcid:");
        stringBuilder.append(realmGet$rcid() != null ? realmGet$rcid() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time_start:");
        stringBuilder.append(realmGet$time_start() != null ? realmGet$time_start() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{time_end:");
        stringBuilder.append(realmGet$time_end() != null ? realmGet$time_end() : "null");
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
        RcapDetailRealmProxy aRcapDetail = (RcapDetailRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aRcapDetail.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aRcapDetail.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aRcapDetail.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
