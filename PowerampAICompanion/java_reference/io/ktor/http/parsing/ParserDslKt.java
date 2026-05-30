package io.ktor.http.parsing;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.RouterConsts;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ParserDsl.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a'\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0080\b\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0003H\u0000\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\u0004\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0080\u0004\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0080\u0004¨\u0006\u0019"}, d2 = {"anyOf", "Lio/ktor/http/parsing/Grammar;", "value", "", "atLeastOne", "grammar", "many", "maybe", "Lkotlin/Function0;", "block", "Lkotlin/Function1;", "Lio/ktor/http/parsing/GrammarBuilder;", "", "Lkotlin/ExtensionFunctionType;", "flatten", "", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/http/parsing/ComplexGrammar;", "named", "name", "or", "then", TypedValues.TransitionType.S_TO, "", RouterConsts.DEVICE_NAME_OTHER, "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ParserDslKt {
    public static final Grammar maybe(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new MaybeGrammar(grammar);
    }

    public static final Grammar maybe(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new MaybeGrammar(new StringGrammar(value));
    }

    public static final Function0<Grammar> maybe(final Function1<? super GrammarBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return new Function0<Grammar>() { // from class: io.ktor.http.parsing.ParserDslKt$maybe$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Grammar invoke() {
                GrammarBuilder grammarBuilder = new GrammarBuilder();
                block.invoke(grammarBuilder);
                return ParserDslKt.maybe(grammarBuilder.build());
            }
        };
    }

    public static final Grammar then(String $this$then, Grammar grammar) {
        Intrinsics.checkNotNullParameter($this$then, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return then(new StringGrammar($this$then), grammar);
    }

    public static final Grammar then(Grammar $this$then, Grammar grammar) {
        Intrinsics.checkNotNullParameter($this$then, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new SequenceGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{$this$then, grammar}));
    }

    public static final Grammar then(Grammar $this$then, String value) {
        Intrinsics.checkNotNullParameter($this$then, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return then($this$then, new StringGrammar(value));
    }

    public static final Grammar or(Grammar $this$or, Grammar grammar) {
        Intrinsics.checkNotNullParameter($this$or, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new OrGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{$this$or, grammar}));
    }

    public static final Grammar or(Grammar $this$or, String value) {
        Intrinsics.checkNotNullParameter($this$or, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return or($this$or, new StringGrammar(value));
    }

    public static final Grammar or(String $this$or, Grammar grammar) {
        Intrinsics.checkNotNullParameter($this$or, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return or(new StringGrammar($this$or), grammar);
    }

    public static final Grammar many(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new ManyGrammar(grammar);
    }

    public static final Grammar atLeastOne(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new AtLeastOne(grammar);
    }

    public static final Grammar named(Grammar $this$named, String name) {
        Intrinsics.checkNotNullParameter($this$named, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        return new NamedGrammar(name, $this$named);
    }

    public static final Grammar anyOf(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new AnyOfGrammar(value);
    }

    public static final Grammar to(char $this$to, char other) {
        return new RangeGrammar($this$to, other);
    }

    public static final /* synthetic */ <T extends ComplexGrammar> List<Grammar> flatten(List<? extends Grammar> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        List result = new ArrayList();
        List<? extends Grammar> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            Object obj = (Grammar) element$iv;
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (obj instanceof ComplexGrammar) {
                CollectionsKt.addAll(result, ((ComplexGrammar) obj).getGrammars());
            } else {
                result.add(obj);
            }
        }
        return result;
    }
}
