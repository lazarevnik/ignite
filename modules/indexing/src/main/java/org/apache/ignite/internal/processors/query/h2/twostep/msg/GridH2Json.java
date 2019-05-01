package org.apache.ignite.internal.processors.query.h2.twostep.msg;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.GridKernalContext;
import org.h2.message.DbException;
import org.h2.value.Value;
import org.h2.value.ValueJson;

/**
 * H2 Json
 */
public class GridH2Json extends GridH2ValueMessage {
    private String value;

    GridH2Json(){
        // No-op.
    }

    GridH2Json(Value val) {
        assert val.getType() == Value.JSON : val.getType();
        value = val.getString();
    }

    @Override
    public Value value(GridKernalContext ctx) throws IgniteCheckedException {
        try {
            return (Value) ValueJson.get(value);
        }
        catch (DbException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public short directType() {
        return -23;
    }

    @Override
    public byte fieldsCount() {
        return 1;
    }

    @Override public String toString() { return value.concat("::JSON"); }
}
