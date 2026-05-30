package io.ktor.util.pipeline;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelinePhaseRelation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Pipeline.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010!\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001BU\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012?\u0010\f\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\u000b0\u0006ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eB\u001b\u0012\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u000f\"\u00020\u0004¢\u0006\u0004\b\r\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015JQ\u0010\u0017\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00160\u0006H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J3\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ#\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u001a\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J#\u0010#\u001a\u00020\"2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\b#\u0010$J%\u0010&\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010%2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b+\u0010,J\u001d\u0010.\u001a\u00020\n2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u00020\n2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b0\u0010/J\u001f\u00103\u001a\u00020\"2\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0004H\u0002¢\u0006\u0004\b3\u00104JS\u00106\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u000429\u00105\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\u000bø\u0001\u0000¢\u0006\u0004\b6\u00107JQ\u00108\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b\u000b0\u00062\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b8\u00109JQ\u0010;\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00160\u0006H\u0000¢\u0006\u0004\b:\u0010\u0018J!\u0010<\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b<\u0010=J#\u0010>\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\b>\u0010=J!\u0010?\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b?\u0010=JY\u0010A\u001a\u00020\n2H\u0010@\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00160\u0006H\u0002¢\u0006\u0004\bA\u0010BJY\u0010D\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00160\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\bC\u00109J!\u0010E\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\bE\u0010=J\u000f\u0010F\u001a\u00020\nH\u0002¢\u0006\u0004\bF\u0010\u0015J#\u0010H\u001a\u00020\n2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\bH\u0010=J#\u0010J\u001a\u00020\n2\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010%H\u0002¢\u0006\u0004\bJ\u0010KJQ\u0010L\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00160\u0006H\u0002¢\u0006\u0004\bL\u0010\u0018J[\u0010M\u001a\u00020\"2\u0006\u0010\u0005\u001a\u00020\u00042B\u00105\u001a>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0016H\u0002¢\u0006\u0004\bM\u0010NR\u0017\u0010P\u001a\u00020O8\u0006¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u001a\u0010T\u001a\u00020\"8\u0016X\u0096D¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR¬\u0001\u0010\f\u001aF\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0016\u0018\u00010\u00062J\u0010X\u001aF\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0016\u0018\u00010\u00068B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bY\u0010\u0018\"\u0004\bZ\u0010BR\u0016\u0010[\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010UR\u0018\u0010\\\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0011\u0010`\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b`\u0010WR\u0017\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068F¢\u0006\u0006\u001a\u0004\ba\u0010\u0018R\u001a\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00010c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lio/ktor/util/pipeline/Pipeline;", "", "TSubject", "TContext", "Lio/ktor/util/pipeline/PipelinePhase;", TypedValues.CycleType.S_WAVE_PHASE, "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "interceptors", "<init>", "(Lio/ktor/util/pipeline/PipelinePhase;Ljava/util/List;)V", "", "phases", "([Lio/ktor/util/pipeline/PipelinePhase;)V", "addPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "afterIntercepted", "()V", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "cacheInterceptors", "()Ljava/util/List;", "context", "subject", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "createContext", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)Lio/ktor/util/pipeline/PipelineContext;", "execute", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", TypedValues.TransitionType.S_FROM, "", "fastPathMerge", "(Lio/ktor/util/pipeline/Pipeline;)Z", "Lio/ktor/util/pipeline/PhaseContent;", "findPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Lio/ktor/util/pipeline/PhaseContent;", "", "findPhaseIndex", "(Lio/ktor/util/pipeline/PipelinePhase;)I", "hasPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Z", "reference", "insertPhaseAfter", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/PipelinePhase;)V", "insertPhaseBefore", "fromPhaseOrContent", "fromPhase", "insertRelativePhase", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelinePhase;)Z", "block", "intercept", "(Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function3;)V", "interceptorsForPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Ljava/util/List;", "interceptorsForTests$ktor_utils", "interceptorsForTests", "merge", "(Lio/ktor/util/pipeline/Pipeline;)V", "mergeInterceptors", "mergePhases", "list", "notSharedInterceptorsList", "(Ljava/util/List;)V", "phaseInterceptors$ktor_utils", "phaseInterceptors", "resetFrom", "resetInterceptorsList", "pipeline", "setInterceptorsListFromAnotherPipeline", "phaseContent", "setInterceptorsListFromPhase", "(Lio/ktor/util/pipeline/PhaseContent;)V", "sharedInterceptorsList", "tryAddToPhaseFastPath", "(Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function3;)Z", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "developmentMode", "Z", "getDevelopmentMode", "()Z", "value", "getInterceptors", "setInterceptors", "interceptorsListShared", "interceptorsListSharedPhase", "Lio/ktor/util/pipeline/PipelinePhase;", "interceptorsQuantity", "I", "isEmpty", "getItems", "items", "", "phasesRaw", "Ljava/util/List;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public class Pipeline<TSubject, TContext> {
    private volatile /* synthetic */ Object _interceptors;
    private final Attributes attributes;
    private final boolean developmentMode;
    private boolean interceptorsListShared;
    private Phase interceptorsListSharedPhase;
    private int interceptorsQuantity;
    private final List<Object> phasesRaw;

    public Pipeline(Phase... phases) {
        Intrinsics.checkNotNullParameter(phases, "phases");
        this.attributes = AttributesJvmKt.Attributes(true);
        this.phasesRaw = CollectionsKt.mutableListOf(Arrays.copyOf(phases, phases.length));
        this._interceptors = null;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final List<Phase> getItems() {
        Iterable $this$map$iv = this.phasesRaw;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Phase phase = item$iv$iv instanceof Phase ? (Phase) item$iv$iv : null;
            if (phase == null) {
                PhaseContent phaseContent = item$iv$iv instanceof PhaseContent ? (PhaseContent) item$iv$iv : null;
                Phase phase2 = phaseContent != null ? phaseContent.getPhase() : null;
                Intrinsics.checkNotNull(phase2);
                phase = phase2;
            }
            destination$iv$iv.add(phase);
        }
        return (List) destination$iv$iv;
    }

    public final boolean isEmpty() {
        return this.interceptorsQuantity == 0;
    }

    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> getInterceptors() {
        return (List) this._interceptors;
    }

    private final void setInterceptors(List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> list) {
        this._interceptors = list;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Pipeline(Phase phase, List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> interceptors) {
        this(phase);
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> $this$forEach$iv = interceptors;
        for (Object element$iv : $this$forEach$iv) {
            Function3 it = (Function3) element$iv;
            intercept(phase, it);
        }
    }

    public final Object execute(TContext tcontext, TSubject tsubject, Continuation<? super TSubject> continuation) {
        return createContext(tcontext, tsubject, continuation.get$context()).execute$ktor_utils(tsubject, continuation);
    }

    public final void addPhase(Phase phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        this.phasesRaw.add(phase);
    }

    public final void insertPhaseAfter(Phase reference, Phase phase) {
        PipelinePhaseRelation relation;
        Phase relatedTo;
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int index = findPhaseIndex(reference);
        if (index == -1) {
            throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
        }
        int lastRelatedPhaseIndex = index;
        int i = index + 1;
        int lastIndex = CollectionsKt.getLastIndex(this.phasesRaw);
        if (i <= lastIndex) {
            while (true) {
                Object obj = this.phasesRaw.get(i);
                PhaseContent phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                if (phaseContent != null && (relation = phaseContent.getRelation()) != null) {
                    PipelinePhaseRelation.After after = relation instanceof PipelinePhaseRelation.After ? (PipelinePhaseRelation.After) relation : null;
                    if (after != null && (relatedTo = after.getRelativeTo()) != null) {
                        lastRelatedPhaseIndex = Intrinsics.areEqual(relatedTo, reference) ? i : lastRelatedPhaseIndex;
                    }
                    if (i == lastIndex) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    break;
                }
            }
        }
        this.phasesRaw.add(lastRelatedPhaseIndex + 1, new PhaseContent(phase, new PipelinePhaseRelation.After(reference)));
    }

    public final void insertPhaseBefore(Phase reference, Phase phase) {
        Intrinsics.checkNotNullParameter(reference, "reference");
        Intrinsics.checkNotNullParameter(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int index = findPhaseIndex(reference);
        if (index == -1) {
            throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
        }
        this.phasesRaw.add(index, new PhaseContent(phase, new PipelinePhaseRelation.Before(reference)));
    }

    public final void intercept(Phase phase, Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(block, "block");
        PhaseContent phaseContent = findPhase(phase);
        if (phaseContent == null) {
            throw new InvalidPhaseException("Phase " + phase + " was not registered for this pipeline");
        }
        if (tryAddToPhaseFastPath(phase, block)) {
            this.interceptorsQuantity++;
            return;
        }
        phaseContent.addInterceptor(block);
        this.interceptorsQuantity++;
        resetInterceptorsList();
        afterIntercepted();
    }

    public void afterIntercepted() {
    }

    public final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptorsForPhase(Phase phase) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(phase, "phase");
        Iterable $this$filterIsInstance$iv = this.phasesRaw;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof PhaseContent) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$firstOrNull$iv = (List) destination$iv$iv;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            PhaseContent phaseOrContent = (PhaseContent) element$iv;
            if (Intrinsics.areEqual(phaseOrContent.getPhase(), phase)) {
                break;
            }
        }
        PhaseContent phaseContent = (PhaseContent) element$iv;
        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptors = phaseContent != null ? phaseContent.sharedInterceptors() : null;
        return sharedInterceptors == null ? CollectionsKt.emptyList() : sharedInterceptors;
    }

    public final void mergePhases(Pipeline<TSubject, TContext> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        List fromPhases = from.phasesRaw;
        List toInsert = CollectionsKt.toMutableList((Collection) fromPhases);
        while (!toInsert.isEmpty()) {
            Iterator iterator = toInsert.iterator();
            while (iterator.hasNext()) {
                Object fromPhaseOrContent = iterator.next();
                Phase fromPhase = fromPhaseOrContent instanceof Phase ? (Phase) fromPhaseOrContent : null;
                if (fromPhase == null) {
                    Intrinsics.checkNotNull(fromPhaseOrContent, "null cannot be cast to non-null type io.ktor.util.pipeline.PhaseContent<*, *>");
                    fromPhase = ((PhaseContent) fromPhaseOrContent).getPhase();
                }
                if (hasPhase(fromPhase)) {
                    iterator.remove();
                } else {
                    boolean inserted = insertRelativePhase(fromPhaseOrContent, fromPhase);
                    if (inserted) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    private final void mergeInterceptors(Pipeline<TSubject, TContext> from) {
        if (this.interceptorsQuantity == 0) {
            setInterceptorsListFromAnotherPipeline(from);
        } else {
            resetInterceptorsList();
        }
        Iterable fromPhases = from.phasesRaw;
        Iterable $this$forEach$iv = fromPhases;
        for (Object element$iv : $this$forEach$iv) {
            Phase fromPhase = element$iv instanceof Phase ? (Phase) element$iv : null;
            if (fromPhase == null) {
                Intrinsics.checkNotNull(element$iv, "null cannot be cast to non-null type io.ktor.util.pipeline.PhaseContent<*, *>");
                fromPhase = ((PhaseContent) element$iv).getPhase();
            }
            if ((element$iv instanceof PhaseContent) && !((PhaseContent) element$iv).isEmpty()) {
                PhaseContent<TSubject, TContext> findPhase = findPhase(fromPhase);
                Intrinsics.checkNotNull(findPhase);
                ((PhaseContent) element$iv).addTo(findPhase);
                this.interceptorsQuantity += ((PhaseContent) element$iv).getSize();
            }
        }
    }

    public final void merge(Pipeline<TSubject, TContext> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        if (fastPathMerge(from)) {
            return;
        }
        mergePhases(from);
        mergeInterceptors(from);
    }

    public final void resetFrom(Pipeline<TSubject, TContext> from) {
        Intrinsics.checkNotNullParameter(from, "from");
        this.phasesRaw.clear();
        if (!(this.interceptorsQuantity == 0)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        fastPathMerge(from);
    }

    public final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> phaseInterceptors$ktor_utils(Phase phase) {
        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptors;
        Intrinsics.checkNotNullParameter(phase, "phase");
        PhaseContent<TSubject, TContext> findPhase = findPhase(phase);
        return (findPhase == null || (sharedInterceptors = findPhase.sharedInterceptors()) == null) ? CollectionsKt.emptyList() : sharedInterceptors;
    }

    public final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptorsForTests$ktor_utils() {
        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptors = getInterceptors();
        return interceptors == null ? cacheInterceptors() : interceptors;
    }

    private final PipelineContext<TSubject, TContext> createContext(TContext context, TSubject subject, CoroutineContext coroutineContext) {
        return PipelineContextKt.pipelineContextFor(context, sharedInterceptorsList(), subject, coroutineContext, getDevelopmentMode());
    }

    private final PhaseContent<TSubject, TContext> findPhase(Phase phase) {
        List phasesList = this.phasesRaw;
        int size = phasesList.size();
        for (int index = 0; index < size; index++) {
            Object current = phasesList.get(index);
            if (current == phase) {
                PhaseContent content = new PhaseContent(phase, PipelinePhaseRelation.Last.INSTANCE);
                phasesList.set(index, content);
                return content;
            }
            if ((current instanceof PhaseContent) && ((PhaseContent) current).getPhase() == phase) {
                return (PhaseContent) current;
            }
        }
        return null;
    }

    private final int findPhaseIndex(Phase phase) {
        List phasesList = this.phasesRaw;
        int size = phasesList.size();
        for (int index = 0; index < size; index++) {
            Object current = phasesList.get(index);
            if (current == phase || ((current instanceof PhaseContent) && ((PhaseContent) current).getPhase() == phase)) {
                return index;
            }
        }
        return -1;
    }

    private final boolean hasPhase(Phase phase) {
        List phasesList = this.phasesRaw;
        int size = phasesList.size();
        for (int index = 0; index < size; index++) {
            Object current = phasesList.get(index);
            if (current == phase) {
                return true;
            }
            if ((current instanceof PhaseContent) && ((PhaseContent) current).getPhase() == phase) {
                return true;
            }
        }
        return false;
    }

    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> cacheInterceptors() {
        int interceptorsQuantity = this.interceptorsQuantity;
        if (interceptorsQuantity == 0) {
            notSharedInterceptorsList(CollectionsKt.emptyList());
            return CollectionsKt.emptyList();
        }
        List phases = this.phasesRaw;
        if (interceptorsQuantity == 1) {
            int phaseIndex = 0;
            int lastIndex = CollectionsKt.getLastIndex(phases);
            if (0 <= lastIndex) {
                while (true) {
                    Object obj = phases.get(phaseIndex);
                    PhaseContent<TSubject, TContext> phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                    if (phaseContent == null || phaseContent.isEmpty()) {
                        if (phaseIndex == lastIndex) {
                            break;
                        }
                        phaseIndex++;
                    } else {
                        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptors = phaseContent.sharedInterceptors();
                        setInterceptorsListFromPhase(phaseContent);
                        return interceptors;
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        int phaseIndex2 = 0;
        int lastIndex2 = CollectionsKt.getLastIndex(phases);
        if (0 <= lastIndex2) {
            while (true) {
                Object obj2 = phases.get(phaseIndex2);
                PhaseContent phase = obj2 instanceof PhaseContent ? (PhaseContent) obj2 : null;
                if (phase != null) {
                    phase.addTo(arrayList);
                }
                if (phaseIndex2 == lastIndex2) {
                    break;
                }
                phaseIndex2++;
            }
        }
        notSharedInterceptorsList(arrayList);
        return arrayList;
    }

    private final boolean fastPathMerge(Pipeline<TSubject, TContext> from) {
        if (from.phasesRaw.isEmpty()) {
            return true;
        }
        if (!this.phasesRaw.isEmpty()) {
            return false;
        }
        List fromPhases = from.phasesRaw;
        int index = 0;
        int lastIndex = CollectionsKt.getLastIndex(fromPhases);
        if (0 <= lastIndex) {
            while (true) {
                Object fromPhaseOrContent = fromPhases.get(index);
                if (fromPhaseOrContent instanceof Phase) {
                    this.phasesRaw.add(fromPhaseOrContent);
                } else if (fromPhaseOrContent instanceof PhaseContent) {
                    this.phasesRaw.add(new PhaseContent(((PhaseContent) fromPhaseOrContent).getPhase(), ((PhaseContent) fromPhaseOrContent).getRelation(), ((PhaseContent) fromPhaseOrContent).sharedInterceptors()));
                }
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        int index2 = this.interceptorsQuantity;
        this.interceptorsQuantity = index2 + from.interceptorsQuantity;
        setInterceptorsListFromAnotherPipeline(from);
        return true;
    }

    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptorsList() {
        if (getInterceptors() == null) {
            cacheInterceptors();
        }
        this.interceptorsListShared = true;
        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptors = getInterceptors();
        Intrinsics.checkNotNull(interceptors);
        return interceptors;
    }

    private final void resetInterceptorsList() {
        setInterceptors(null);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void notSharedInterceptorsList(List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> list) {
        setInterceptors(list);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptorsListFromPhase(PhaseContent<TSubject, TContext> phaseContent) {
        setInterceptors(phaseContent.sharedInterceptors());
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = phaseContent.getPhase();
    }

    private final void setInterceptorsListFromAnotherPipeline(Pipeline<TSubject, TContext> pipeline) {
        setInterceptors(pipeline.sharedInterceptorsList());
        this.interceptorsListShared = true;
        this.interceptorsListSharedPhase = null;
    }

    private final boolean tryAddToPhaseFastPath(Phase phase, Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> block) {
        List currentInterceptors = getInterceptors();
        if (this.phasesRaw.isEmpty() || currentInterceptors == null || this.interceptorsListShared || !TypeIntrinsics.isMutableList(currentInterceptors)) {
            return false;
        }
        if (Intrinsics.areEqual(this.interceptorsListSharedPhase, phase)) {
            currentInterceptors.add(block);
            return true;
        }
        if (!Intrinsics.areEqual(phase, CollectionsKt.last((List) this.phasesRaw)) && findPhaseIndex(phase) != CollectionsKt.getLastIndex(this.phasesRaw)) {
            return false;
        }
        PhaseContent<TSubject, TContext> findPhase = findPhase(phase);
        Intrinsics.checkNotNull(findPhase);
        findPhase.addInterceptor(block);
        currentInterceptors.add(block);
        return true;
    }

    private final boolean insertRelativePhase(Object fromPhaseOrContent, Phase fromPhase) {
        PipelinePhaseRelation.Last fromPhaseRelation;
        if (fromPhaseOrContent == fromPhase) {
            fromPhaseRelation = PipelinePhaseRelation.Last.INSTANCE;
        } else {
            Intrinsics.checkNotNull(fromPhaseOrContent, "null cannot be cast to non-null type io.ktor.util.pipeline.PhaseContent<*, *>");
            fromPhaseRelation = ((PhaseContent) fromPhaseOrContent).getRelation();
        }
        if (fromPhaseRelation instanceof PipelinePhaseRelation.Last) {
            addPhase(fromPhase);
            return true;
        }
        if ((fromPhaseRelation instanceof PipelinePhaseRelation.Before) && hasPhase(((PipelinePhaseRelation.Before) fromPhaseRelation).getRelativeTo())) {
            insertPhaseBefore(((PipelinePhaseRelation.Before) fromPhaseRelation).getRelativeTo(), fromPhase);
            return true;
        }
        if (fromPhaseRelation instanceof PipelinePhaseRelation.After) {
            insertPhaseAfter(((PipelinePhaseRelation.After) fromPhaseRelation).getRelativeTo(), fromPhase);
            return true;
        }
        return false;
    }
}
