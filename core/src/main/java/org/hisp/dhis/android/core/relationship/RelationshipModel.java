package org.hisp.dhis.android.core.relationship;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;

import org.hisp.dhis.android.core.common.BaseModel;

@AutoValue
public abstract class RelationshipModel extends BaseModel {

    public static RelationshipModel create(Cursor cursor) {
        return AutoValue_RelationshipModel.createFromCursor(cursor);
    }

    public static Builder builder() {
        return new $$AutoValue_RelationshipModel.Builder();
    }

    @NonNull
    public abstract ContentValues toContentValues();

    @Nullable
    @ColumnName(RelationshipContract.Columns.TRACKED_ENTITY_INSTANCE_A)
    public abstract String trackedEntityInstanceA();

    @Nullable
    @ColumnName(RelationshipContract.Columns.TRACKED_ENTITY_INSTANCE_B)
    public abstract String trackedEntityInstanceB();

    @Nullable
    @ColumnName(RelationshipContract.Columns.RELATIONSHIP_TYPE)
    public abstract String relationshipType();

    @AutoValue.Builder
    public static abstract class Builder extends BaseModel.Builder<Builder> {

        public abstract Builder trackedEntityInstanceA(@Nullable String trackedEntityInstanceA);

        public abstract Builder trackedEntityInstanceB(@Nullable String trackedEntityInstanceB);

        public abstract Builder relationshipType(@Nullable String relationshipType);

        public abstract RelationshipModel build();
    }
}
