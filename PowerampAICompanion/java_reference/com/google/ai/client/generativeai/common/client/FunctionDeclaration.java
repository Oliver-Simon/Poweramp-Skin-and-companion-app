package com.google.ai.client.generativeai.common.client;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J!\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HÇ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/FunctionDeclaration;", "", "seen1", "", "name", "", "description", "parameters", "Lcom/google/ai/client/generativeai/common/client/Schema;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/client/Schema;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/common/client/Schema;)V", "getDescription", "()Ljava/lang/String;", "getName", "getParameters", "()Lcom/google/ai/client/generativeai/common/client/Schema;", "component1", "component2", "component3", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class FunctionDeclaration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String description;
    private final String name;
    private final Schema parameters;

    public static /* synthetic */ FunctionDeclaration copy$default(FunctionDeclaration functionDeclaration, String str, String str2, Schema schema, int i, Object obj) {
        if ((i & 1) != 0) {
            str = functionDeclaration.name;
        }
        if ((i & 2) != 0) {
            str2 = functionDeclaration.description;
        }
        if ((i & 4) != 0) {
            schema = functionDeclaration.parameters;
        }
        return functionDeclaration.copy(str, str2, schema);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component3, reason: from getter */
    public final Schema getParameters() {
        return this.parameters;
    }

    public final FunctionDeclaration copy(String name, String description, Schema parameters) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        return new FunctionDeclaration(name, description, parameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunctionDeclaration)) {
            return false;
        }
        FunctionDeclaration functionDeclaration = (FunctionDeclaration) other;
        return Intrinsics.areEqual(this.name, functionDeclaration.name) && Intrinsics.areEqual(this.description, functionDeclaration.description) && Intrinsics.areEqual(this.parameters, functionDeclaration.parameters);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + this.parameters.hashCode();
    }

    public String toString() {
        return "FunctionDeclaration(name=" + this.name + ", description=" + this.description + ", parameters=" + this.parameters + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/FunctionDeclaration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/client/FunctionDeclaration;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<FunctionDeclaration> serializer() {
            return FunctionDeclaration$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FunctionDeclaration(int seen1, String name, String description, Schema parameters, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (seen1 & 7)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 7, FunctionDeclaration$$serializer.INSTANCE.getDescriptor());
        }
        this.name = name;
        this.description = description;
        this.parameters = parameters;
    }

    public FunctionDeclaration(String name, String description, Schema parameters) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.name = name;
        this.description = description;
        this.parameters = parameters;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(FunctionDeclaration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.name);
        output.encodeStringElement(serialDesc, 1, self.description);
        output.encodeSerializableElement(serialDesc, 2, Schema$$serializer.INSTANCE, self.parameters);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getName() {
        return this.name;
    }

    public final Schema getParameters() {
        return this.parameters;
    }
}
