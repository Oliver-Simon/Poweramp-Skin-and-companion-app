package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import com.maxmpz.poweramp.equalizer.PeqAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultSpecialEffectsController.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0003*+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J(\u0010\t\u001a\u00020\u00062\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u001e\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u000e\u001a\u00020\fH\u0002J@\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u001d\u001a\u00020\u00132\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001fH\u0002JL\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J\u0016\u0010%\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0002J&\u0010&\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170)H\u0002¨\u0006-"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "applyContainerChanges", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "captureTransitioningViews", "transitioningViews", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "view", "executeOperations", "operations", "", "isPop", "", "findNamedViews", "namedViews", "", "", "startAnimations", "animationInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "awaitingContainerChanges", "", "startedAnyTransition", "startedTransitions", "", "startTransitions", "transitionInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "lastIn", "syncAnimations", "retainMatchingViews", "Landroidx/collection/ArrayMap;", PeqAPI.EXTRA_NAMES, "", "AnimationInfo", "SpecialEffectsInfo", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List<? extends SpecialEffectsController.Operation> operations, boolean isPop) {
        Object element$iv;
        Object obj;
        Intrinsics.checkNotNullParameter(operations, "operations");
        List<? extends SpecialEffectsController.Operation> $this$firstOrNull$iv = operations;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) element$iv;
                SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
                View view = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
                SpecialEffectsController.Operation.State currentState = companion.asOperationState(view);
                if (currentState == SpecialEffectsController.Operation.State.VISIBLE && operation.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        SpecialEffectsController.Operation firstOut = (SpecialEffectsController.Operation) element$iv;
        ListIterator iterator$iv = operations.listIterator(operations.size());
        while (true) {
            if (iterator$iv.hasPrevious()) {
                Object element$iv2 = iterator$iv.previous();
                SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) element$iv2;
                SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.INSTANCE;
                View view2 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
                SpecialEffectsController.Operation.State currentState2 = companion2.asOperationState(view2);
                if (currentState2 != SpecialEffectsController.Operation.State.VISIBLE && operation2.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                    obj = element$iv2;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SpecialEffectsController.Operation lastIn = (SpecialEffectsController.Operation) obj;
        int i = 2;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + firstOut + " to " + lastIn);
        }
        List animations = new ArrayList();
        List transitions = new ArrayList();
        final List awaitingContainerChanges = CollectionsKt.toMutableList((Collection) operations);
        syncAnimations(operations);
        Iterator<? extends SpecialEffectsController.Operation> it2 = operations.iterator();
        while (it2.hasNext()) {
            final SpecialEffectsController.Operation operation3 = it2.next();
            CancellationSignal animCancellationSignal = new CancellationSignal();
            operation3.markStartedSpecialEffect(animCancellationSignal);
            int i2 = i;
            animations.add(new AnimationInfo(operation3, animCancellationSignal, isPop));
            CancellationSignal transitionCancellationSignal = new CancellationSignal();
            operation3.markStartedSpecialEffect(transitionCancellationSignal);
            transitions.add(new TransitionInfo(operation3, transitionCancellationSignal, isPop, !isPop ? operation3 != lastIn : operation3 != firstOut));
            operation3.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultSpecialEffectsController.executeOperations$lambda$2(awaitingContainerChanges, operation3, this);
                }
            });
            i = i2;
        }
        int i3 = i;
        Map startedTransitions = startTransitions(transitions, awaitingContainerChanges, isPop, firstOut, lastIn);
        boolean startedAnyTransition = startedTransitions.containsValue(true);
        startAnimations(animations, awaitingContainerChanges, startedAnyTransition, startedTransitions);
        Iterator<SpecialEffectsController.Operation> it3 = awaitingContainerChanges.iterator();
        while (it3.hasNext()) {
            applyContainerChanges(it3.next());
        }
        awaitingContainerChanges.clear();
        if (FragmentManager.isLoggingEnabled(i3)) {
            Log.v(FragmentManager.TAG, "Completed executing operations from " + firstOut + " to " + lastIn);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List awaitingContainerChanges, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController this$0) {
        Intrinsics.checkNotNullParameter(awaitingContainerChanges, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (awaitingContainerChanges.contains(operation)) {
            awaitingContainerChanges.remove(operation);
            this$0.applyContainerChanges(operation);
        }
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> operations) {
        Fragment lastOpFragment = ((SpecialEffectsController.Operation) CollectionsKt.last((List) operations)).getFragment();
        for (SpecialEffectsController.Operation operation : operations) {
            operation.getFragment().mAnimationInfo.mEnterAnim = lastOpFragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = lastOpFragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = lastOpFragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = lastOpFragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void startAnimations(List<AnimationInfo> animationInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, boolean startedAnyTransition, Map<SpecialEffectsController.Operation, Boolean> startedTransitions) {
        int i;
        boolean startedAnyAnimator;
        Context context = getContainer().getContext();
        List<AnimationInfo> animationsToRun = new ArrayList();
        boolean startedAnyAnimator2 = false;
        Iterator<AnimationInfo> it = animationInfos.iterator();
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            final AnimationInfo animationInfo = it.next();
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator anim = animationInfo.getAnimation(context);
                if (anim == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = anim.animator;
                    if (animator == null) {
                        animationsToRun.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        boolean startedTransition = Intrinsics.areEqual((Object) startedTransitions.get(operation), (Object) true);
                        if (startedTransition) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean isHideOperation = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (isHideOperation) {
                                awaitingContainerChanges.remove(operation);
                            }
                            final View viewToAnimate = fragment.mView;
                            getContainer().startViewTransition(viewToAnimate);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$startAnimations$1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator anim2) {
                                    Intrinsics.checkNotNullParameter(anim2, "anim");
                                    DefaultSpecialEffectsController.this.getContainer().endViewTransition(viewToAnimate);
                                    if (isHideOperation) {
                                        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                                        View viewToAnimate2 = viewToAnimate;
                                        Intrinsics.checkNotNullExpressionValue(viewToAnimate2, "viewToAnimate");
                                        finalState.applyState(viewToAnimate2);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(viewToAnimate);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has started.");
                            }
                            CancellationSignal signal = animationInfo.getSignal();
                            signal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public final void onCancel() {
                                    DefaultSpecialEffectsController.startAnimations$lambda$3(animator, operation);
                                }
                            });
                            startedAnyAnimator2 = true;
                        }
                    }
                }
            }
        }
        for (final AnimationInfo animationInfo2 : animationsToRun) {
            final SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (startedAnyTransition) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (startedAnyAnimator2) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View viewToAnimate2 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo2.getAnimation(context);
                if (animation == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Animation anim2 = animation.animation;
                if (anim2 == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                SpecialEffectsController.Operation.State finalState = operation2.getFinalState();
                int i2 = i;
                if (finalState != SpecialEffectsController.Operation.State.REMOVED) {
                    viewToAnimate2.startAnimation(anim2);
                    animationInfo2.completeSpecialEffect();
                    startedAnyAnimator = startedAnyAnimator2;
                } else {
                    getContainer().startViewTransition(viewToAnimate2);
                    startedAnyAnimator = startedAnyAnimator2;
                    Animation animation2 = new FragmentAnim.EndViewTransitionAnimation(anim2, getContainer(), viewToAnimate2);
                    animation2.setAnimationListener(new DefaultSpecialEffectsController$startAnimations$3(operation2, this, viewToAnimate2, animationInfo2));
                    viewToAnimate2.startAnimation(animation2);
                    if (FragmentManager.isLoggingEnabled(i2)) {
                        Log.v(FragmentManager.TAG, "Animation from operation " + operation2 + " has started.");
                    }
                }
                CancellationSignal signal2 = animationInfo2.getSignal();
                signal2.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda1
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        DefaultSpecialEffectsController.startAnimations$lambda$4(viewToAnimate2, this, animationInfo2, operation2);
                    }
                });
                i = i2;
                startedAnyAnimator2 = startedAnyAnimator;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator $animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        $animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View $viewToAnimate, DefaultSpecialEffectsController this$0, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        $viewToAnimate.clearAnimation();
        this$0.getContainer().endViewTransition($viewToAnimate);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has been cancelled.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> transitionInfos, List<SpecialEffectsController.Operation> awaitingContainerChanges, boolean isPop, SpecialEffectsController.Operation firstOut, SpecialEffectsController.Operation lastIn) {
        String str;
        String str2;
        ArrayList enteringViews;
        Rect lastInEpicenterRect;
        Iterator<TransitionInfo> it;
        ArrayList sharedElementFirstOutViews;
        ArrayMap sharedElementNameMapping;
        final ArrayList transitioningViews;
        View nonExistentView;
        View firstOutEpicenterView;
        Rect lastInEpicenterRect2;
        Object sharedElementTransition;
        View firstOutEpicenterView2;
        final Rect lastInEpicenterRect3;
        int i;
        int i2;
        SharedElementCallback exitingCallback;
        DefaultSpecialEffectsController defaultSpecialEffectsController = this;
        final boolean z = isPop;
        final SpecialEffectsController.Operation operation = firstOut;
        final SpecialEffectsController.Operation operation2 = lastIn;
        Map startedTransitions = new LinkedHashMap();
        List<TransitionInfo> $this$filterNot$iv = transitionInfos;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            if (!((TransitionInfo) element$iv$iv).isVisibilityUnchanged()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv) {
            if (((TransitionInfo) element$iv$iv2).getHandlingImpl() != null) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Iterable $this$fold$iv = (List) destination$iv$iv2;
        FragmentTransitionImpl transitionImpl = null;
        for (Object element$iv : $this$fold$iv) {
            TransitionInfo transitionInfo = (TransitionInfo) element$iv;
            FragmentTransitionImpl chosenImpl = transitionImpl;
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (!(chosenImpl == null || handlingImpl == chosenImpl)) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
            transitionImpl = handlingImpl;
        }
        int i3 = 0;
        if (transitionImpl == null) {
            for (TransitionInfo transitionInfo2 : transitionInfos) {
                startedTransitions.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return startedTransitions;
        }
        View nonExistentView2 = new View(defaultSpecialEffectsController.getContainer().getContext());
        Object sharedElementTransition2 = null;
        View firstOutEpicenterView3 = null;
        boolean hasLastInEpicenter = false;
        Rect lastInEpicenterRect4 = new Rect();
        ArrayList sharedElementFirstOutViews2 = new ArrayList();
        ArrayList sharedElementLastInViews = new ArrayList();
        ArrayMap sharedElementNameMapping2 = new ArrayMap();
        Iterator<TransitionInfo> it2 = transitionInfos.iterator();
        while (true) {
            boolean hasNext = it2.hasNext();
            str = FragmentManager.TAG;
            if (!hasNext) {
                break;
            }
            TransitionInfo transitionInfo3 = it2.next();
            boolean hasSharedElementTransition = transitionInfo3.hasSharedElementTransition();
            if (!hasSharedElementTransition || operation == null || operation2 == null) {
                lastInEpicenterRect4 = lastInEpicenterRect4;
                startedTransitions = startedTransitions;
                hasLastInEpicenter = hasLastInEpicenter;
                it2 = it2;
                firstOutEpicenterView3 = firstOutEpicenterView3;
                i3 = 0;
                nonExistentView2 = nonExistentView2;
                z = isPop;
            } else {
                boolean hasLastInEpicenter2 = hasLastInEpicenter;
                Object sharedElementTransition3 = transitionImpl.wrapTransitionInSet(transitionImpl.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList exitingNames = operation2.getFragment().getSharedElementSourceNames();
                Iterator<TransitionInfo> it3 = it2;
                Intrinsics.checkNotNullExpressionValue(exitingNames, "lastIn.fragment.sharedElementSourceNames");
                ArrayList firstOutSourceNames = operation.getFragment().getSharedElementSourceNames();
                View firstOutEpicenterView4 = firstOutEpicenterView3;
                Intrinsics.checkNotNullExpressionValue(firstOutSourceNames, "firstOut.fragment.sharedElementSourceNames");
                ArrayList firstOutTargetNames = operation.getFragment().getSharedElementTargetNames();
                Map startedTransitions2 = startedTransitions;
                Intrinsics.checkNotNullExpressionValue(firstOutTargetNames, "firstOut.fragment.sharedElementTargetNames");
                int size = firstOutTargetNames.size();
                View nonExistentView3 = nonExistentView2;
                int index = 0;
                while (true) {
                    lastInEpicenterRect2 = lastInEpicenterRect4;
                    if (index >= size) {
                        break;
                    }
                    int nameIndex = exitingNames.indexOf(firstOutTargetNames.get(index));
                    int i4 = size;
                    if (nameIndex != -1) {
                        exitingNames.set(nameIndex, firstOutSourceNames.get(index));
                    }
                    index++;
                    size = i4;
                    lastInEpicenterRect4 = lastInEpicenterRect2;
                }
                ArrayList enteringNames = operation2.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(enteringNames, "lastIn.fragment.sharedElementTargetNames");
                Pair pair = !z ? TuplesKt.to(operation.getFragment().getExitTransitionCallback(), operation2.getFragment().getEnterTransitionCallback()) : TuplesKt.to(operation.getFragment().getEnterTransitionCallback(), operation2.getFragment().getExitTransitionCallback());
                SharedElementCallback exitingCallback2 = (SharedElementCallback) pair.component1();
                SharedElementCallback enteringCallback = (SharedElementCallback) pair.component2();
                int numSharedElements = exitingNames.size();
                int i5 = 0;
                while (i5 < numSharedElements) {
                    int numSharedElements2 = numSharedElements;
                    String exitingName = exitingNames.get(i5);
                    int i6 = i5;
                    String enteringName = enteringNames.get(i5);
                    sharedElementNameMapping2.put(exitingName, enteringName);
                    i5 = i6 + 1;
                    sharedElementLastInViews = sharedElementLastInViews;
                    numSharedElements = numSharedElements2;
                }
                ArrayList sharedElementLastInViews2 = sharedElementLastInViews;
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, ">>> entering view names <<<");
                    for (Iterator<String> it4 = enteringNames.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v(FragmentManager.TAG, "Name: " + it4.next());
                    }
                    Log.v(FragmentManager.TAG, ">>> exiting view names <<<");
                    for (Iterator<String> it5 = exitingNames.iterator(); it5.hasNext(); it5 = it5) {
                        Log.v(FragmentManager.TAG, "Name: " + it5.next());
                    }
                }
                ArrayMap firstOutViews = new ArrayMap();
                View view = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view, "firstOut.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(firstOutViews, view);
                firstOutViews.retainAll(exitingNames);
                if (exitingCallback2 != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing exit callback for operation " + operation);
                    }
                    exitingCallback2.onMapSharedElements(exitingNames, firstOutViews);
                    int size2 = exitingNames.size() - 1;
                    if (size2 >= 0) {
                        while (true) {
                            int i7 = size2;
                            int i8 = size2 - 1;
                            String name = (String) exitingNames.get(i7);
                            View view2 = firstOutViews.get(name);
                            if (view2 == null) {
                                sharedElementNameMapping2.remove(name);
                                i2 = i8;
                                exitingCallback = exitingCallback2;
                            } else {
                                i2 = i8;
                                if (Intrinsics.areEqual(name, ViewCompat.getTransitionName(view2))) {
                                    exitingCallback = exitingCallback2;
                                } else {
                                    String targetValue = (String) sharedElementNameMapping2.remove(name);
                                    exitingCallback = exitingCallback2;
                                    sharedElementNameMapping2.put(ViewCompat.getTransitionName(view2), targetValue);
                                }
                            }
                            if (i2 < 0) {
                                break;
                            }
                            size2 = i2;
                            exitingCallback2 = exitingCallback;
                        }
                    }
                } else {
                    sharedElementNameMapping2.retainAll(firstOutViews.keySet());
                }
                final ArrayMap lastInViews = new ArrayMap();
                View view3 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view3, "lastIn.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(lastInViews, view3);
                lastInViews.retainAll(enteringNames);
                lastInViews.retainAll(sharedElementNameMapping2.values());
                if (enteringCallback != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Executing enter callback for operation " + operation2);
                    }
                    enteringCallback.onMapSharedElements(enteringNames, lastInViews);
                    int size3 = enteringNames.size() - 1;
                    if (size3 >= 0) {
                        while (true) {
                            int i9 = size3;
                            int i10 = size3 - 1;
                            String name2 = enteringNames.get(i9);
                            View view4 = lastInViews.get(name2);
                            SharedElementCallback enteringCallback2 = enteringCallback;
                            if (view4 == null) {
                                Intrinsics.checkNotNullExpressionValue(name2, "name");
                                String key = FragmentTransition.findKeyForValue(sharedElementNameMapping2, name2);
                                if (key != null) {
                                    sharedElementNameMapping2.remove(key);
                                    i = i10;
                                } else {
                                    i = i10;
                                }
                            } else {
                                i = i10;
                                if (!Intrinsics.areEqual(name2, ViewCompat.getTransitionName(view4))) {
                                    Intrinsics.checkNotNullExpressionValue(name2, "name");
                                    String key2 = FragmentTransition.findKeyForValue(sharedElementNameMapping2, name2);
                                    if (key2 != null) {
                                        sharedElementNameMapping2.put(key2, ViewCompat.getTransitionName(view4));
                                    }
                                }
                            }
                            if (i < 0) {
                                break;
                            }
                            size3 = i;
                            enteringCallback = enteringCallback2;
                        }
                    }
                } else {
                    FragmentTransition.retainValues(sharedElementNameMapping2, lastInViews);
                }
                Set keySet = sharedElementNameMapping2.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "sharedElementNameMapping.keys");
                defaultSpecialEffectsController.retainMatchingViews(firstOutViews, keySet);
                Collection<String> values = sharedElementNameMapping2.values();
                Intrinsics.checkNotNullExpressionValue(values, "sharedElementNameMapping.values");
                defaultSpecialEffectsController.retainMatchingViews(lastInViews, values);
                if (sharedElementNameMapping2.isEmpty()) {
                    sharedElementFirstOutViews2.clear();
                    sharedElementLastInViews2.clear();
                    sharedElementTransition2 = null;
                    sharedElementLastInViews = sharedElementLastInViews2;
                    hasLastInEpicenter = hasLastInEpicenter2;
                    it2 = it3;
                    firstOutEpicenterView3 = firstOutEpicenterView4;
                    startedTransitions = startedTransitions2;
                    nonExistentView2 = nonExistentView3;
                    lastInEpicenterRect4 = lastInEpicenterRect2;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, firstOutViews, true);
                    OneShotPreDrawListener.add(defaultSpecialEffectsController.getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.startTransitions$lambda$9(SpecialEffectsController.Operation.this, operation, z, lastInViews);
                        }
                    });
                    sharedElementFirstOutViews2.addAll(firstOutViews.values());
                    if (exitingNames.isEmpty()) {
                        sharedElementTransition = sharedElementTransition3;
                        firstOutEpicenterView2 = firstOutEpicenterView4;
                    } else {
                        String epicenterViewName = (String) exitingNames.get(i3);
                        firstOutEpicenterView2 = firstOutViews.get(epicenterViewName);
                        sharedElementTransition = sharedElementTransition3;
                        transitionImpl.setEpicenter(sharedElementTransition, firstOutEpicenterView2);
                    }
                    sharedElementLastInViews = sharedElementLastInViews2;
                    sharedElementLastInViews.addAll(lastInViews.values());
                    if (enteringNames.isEmpty()) {
                        lastInEpicenterRect3 = lastInEpicenterRect2;
                    } else {
                        String epicenterViewName2 = (String) enteringNames.get(0);
                        final View lastInEpicenterView = lastInViews.get(epicenterViewName2);
                        if (lastInEpicenterView != null) {
                            final FragmentTransitionImpl impl = transitionImpl;
                            lastInEpicenterRect3 = lastInEpicenterRect2;
                            OneShotPreDrawListener.add(defaultSpecialEffectsController.getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.startTransitions$lambda$10(FragmentTransitionImpl.this, lastInEpicenterView, lastInEpicenterRect3);
                                }
                            });
                            hasLastInEpicenter2 = true;
                        } else {
                            lastInEpicenterRect3 = lastInEpicenterRect2;
                        }
                    }
                    transitionImpl.setSharedElementTargets(sharedElementTransition, nonExistentView3, sharedElementFirstOutViews2);
                    Object sharedElementTransition4 = sharedElementTransition;
                    transitionImpl.scheduleRemoveTargets(sharedElementTransition4, null, null, null, null, sharedElementTransition, sharedElementLastInViews);
                    startedTransitions2.put(operation, true);
                    startedTransitions2.put(operation2, true);
                    firstOutEpicenterView3 = firstOutEpicenterView2;
                    startedTransitions = startedTransitions2;
                    sharedElementTransition2 = sharedElementTransition4;
                    hasLastInEpicenter = hasLastInEpicenter2;
                    it2 = it3;
                    i3 = 0;
                    lastInEpicenterRect4 = lastInEpicenterRect3;
                    nonExistentView2 = nonExistentView3;
                    z = isPop;
                }
            }
        }
        Map startedTransitions3 = startedTransitions;
        View nonExistentView4 = nonExistentView2;
        View firstOutEpicenterView5 = firstOutEpicenterView3;
        boolean hasLastInEpicenter3 = hasLastInEpicenter;
        Rect lastInEpicenterRect5 = lastInEpicenterRect4;
        ArrayList enteringViews2 = new ArrayList();
        Object mergedTransition = null;
        Object mergedNonOverlappingTransition = null;
        Iterator<TransitionInfo> it6 = transitionInfos.iterator();
        while (it6.hasNext()) {
            TransitionInfo transitionInfo4 = it6.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                it = it6;
                sharedElementFirstOutViews = sharedElementFirstOutViews2;
                startedTransitions3.put(transitionInfo4.getOperation(), false);
                transitionInfo4.completeSpecialEffect();
                sharedElementNameMapping = sharedElementNameMapping2;
            } else {
                it = it6;
                sharedElementFirstOutViews = sharedElementFirstOutViews2;
                Object transition = transitionImpl.cloneTransition(transitionInfo4.getTransition());
                SpecialEffectsController.Operation operation3 = transitionInfo4.getOperation();
                boolean involvedInSharedElementTransition = sharedElementTransition2 != null && (operation3 == operation || operation3 == operation2);
                if (transition != null) {
                    ArrayMap sharedElementNameMapping3 = sharedElementNameMapping2;
                    ArrayList transitioningViews2 = new ArrayList();
                    ArrayList sharedElementLastInViews3 = sharedElementLastInViews;
                    View view5 = operation3.getFragment().mView;
                    String str3 = str;
                    Intrinsics.checkNotNullExpressionValue(view5, "operation.fragment.mView");
                    defaultSpecialEffectsController.captureTransitioningViews(transitioningViews2, view5);
                    if (involvedInSharedElementTransition) {
                        if (operation3 == operation) {
                            transitioningViews2.removeAll(CollectionsKt.toSet(sharedElementFirstOutViews));
                        } else {
                            transitioningViews2.removeAll(CollectionsKt.toSet(sharedElementLastInViews3));
                        }
                    }
                    if (transitioningViews2.isEmpty()) {
                        transitionImpl.addTarget(transition, nonExistentView4);
                        nonExistentView = nonExistentView4;
                        transitioningViews = transitioningViews2;
                    } else {
                        transitionImpl.addTargets(transition, transitioningViews2);
                        transitionImpl.scheduleRemoveTargets(transition, transition, transitioningViews2, null, null, null, null);
                        transitioningViews = transitioningViews2;
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            awaitingContainerChanges.remove(operation3);
                            ArrayList transitioningViewsToHide = new ArrayList(transitioningViews);
                            transitioningViewsToHide.remove(operation3.getFragment().mView);
                            transitionImpl.scheduleHideFragmentView(transition, operation3.getFragment().mView, transitioningViewsToHide);
                            nonExistentView = nonExistentView4;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.startTransitions$lambda$11(transitioningViews);
                                }
                            });
                        } else {
                            nonExistentView = nonExistentView4;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        enteringViews2.addAll(transitioningViews);
                        if (hasLastInEpicenter3) {
                            transitionImpl.setEpicenter(transition, lastInEpicenterRect5);
                            firstOutEpicenterView = firstOutEpicenterView5;
                        } else {
                            firstOutEpicenterView = firstOutEpicenterView5;
                        }
                    } else {
                        firstOutEpicenterView = firstOutEpicenterView5;
                        transitionImpl.setEpicenter(transition, firstOutEpicenterView);
                    }
                    startedTransitions3.put(operation3, true);
                    if (transitionInfo4.getIsOverlapAllowed()) {
                        mergedTransition = transitionImpl.mergeTransitionsTogether(mergedTransition, transition, null);
                        firstOutEpicenterView5 = firstOutEpicenterView;
                        nonExistentView4 = nonExistentView;
                        it6 = it;
                        sharedElementFirstOutViews2 = sharedElementFirstOutViews;
                        sharedElementNameMapping2 = sharedElementNameMapping3;
                        sharedElementLastInViews = sharedElementLastInViews3;
                        str = str3;
                        defaultSpecialEffectsController = this;
                    } else {
                        mergedNonOverlappingTransition = transitionImpl.mergeTransitionsTogether(mergedNonOverlappingTransition, transition, null);
                        firstOutEpicenterView5 = firstOutEpicenterView;
                        nonExistentView4 = nonExistentView;
                        it6 = it;
                        sharedElementFirstOutViews2 = sharedElementFirstOutViews;
                        sharedElementNameMapping2 = sharedElementNameMapping3;
                        sharedElementLastInViews = sharedElementLastInViews3;
                        str = str3;
                        defaultSpecialEffectsController = this;
                    }
                } else if (involvedInSharedElementTransition) {
                    sharedElementNameMapping = sharedElementNameMapping2;
                } else {
                    sharedElementNameMapping = sharedElementNameMapping2;
                    startedTransitions3.put(operation3, false);
                    transitionInfo4.completeSpecialEffect();
                }
            }
            it6 = it;
            sharedElementFirstOutViews2 = sharedElementFirstOutViews;
            sharedElementNameMapping2 = sharedElementNameMapping;
        }
        ArrayList sharedElementFirstOutViews3 = sharedElementFirstOutViews2;
        String str4 = str;
        ArrayList sharedElementLastInViews4 = sharedElementLastInViews;
        ArrayMap sharedElementNameMapping4 = sharedElementNameMapping2;
        Object element$iv$iv3 = firstOutEpicenterView5;
        Object mergedTransition2 = transitionImpl.mergeTransitionsInSequence(mergedTransition, mergedNonOverlappingTransition, sharedElementTransition2);
        if (mergedTransition2 == null) {
            return startedTransitions3;
        }
        List<TransitionInfo> $this$filterNot$iv2 = transitionInfos;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv4 : $this$filterNot$iv2) {
            Object firstOutEpicenterView6 = element$iv$iv3;
            if (!((TransitionInfo) element$iv$iv4).isVisibilityUnchanged()) {
                destination$iv$iv3.add(element$iv$iv4);
            }
            element$iv$iv3 = firstOutEpicenterView6;
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv3;
        for (Object element$iv2 : $this$forEach$iv) {
            final TransitionInfo transitionInfo5 = (TransitionInfo) element$iv2;
            Object transition2 = transitionInfo5.getTransition();
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            final SpecialEffectsController.Operation operation4 = transitionInfo5.getOperation();
            boolean involvedInSharedElementTransition2 = sharedElementTransition2 != null && (operation4 == operation || operation4 == operation2);
            if (transition2 == null && !involvedInSharedElementTransition2) {
                enteringViews = enteringViews2;
                lastInEpicenterRect = lastInEpicenterRect5;
                str2 = str4;
            } else if (ViewCompat.isLaidOut(getContainer())) {
                str2 = str4;
                enteringViews = enteringViews2;
                lastInEpicenterRect = lastInEpicenterRect5;
                transitionImpl.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergedTransition2, transitionInfo5.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSpecialEffectsController.startTransitions$lambda$14$lambda$13(DefaultSpecialEffectsController.TransitionInfo.this, operation4);
                    }
                });
            } else {
                if (FragmentManager.isLoggingEnabled(2)) {
                    str2 = str4;
                    Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation4);
                } else {
                    str2 = str4;
                }
                transitionInfo5.completeSpecialEffect();
                enteringViews = enteringViews2;
                lastInEpicenterRect = lastInEpicenterRect5;
            }
            operation = firstOut;
            str4 = str2;
            $this$forEach$iv = $this$forEach$iv2;
            enteringViews2 = enteringViews;
            lastInEpicenterRect5 = lastInEpicenterRect;
            operation2 = lastIn;
        }
        ArrayList enteringViews3 = enteringViews2;
        String str5 = str4;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return startedTransitions3;
        }
        FragmentTransition.setViewVisibility(enteringViews3, 4);
        ArrayList inNames = transitionImpl.prepareSetNameOverridesReordered(sharedElementLastInViews4);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str5, ">>>>> Beginning transition <<<<<");
            Log.v(str5, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator<View> it7 = sharedElementFirstOutViews3.iterator();
            while (it7.hasNext()) {
                View sharedElementFirstOutViews4 = it7.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews4, "sharedElementFirstOutViews");
                View view6 = sharedElementFirstOutViews4;
                Log.v(str5, "View: " + view6 + " Name: " + ViewCompat.getTransitionName(view6));
            }
            Log.v(str5, ">>>>> SharedElementLastInViews <<<<<");
            Iterator<View> it8 = sharedElementLastInViews4.iterator();
            while (it8.hasNext()) {
                View sharedElementLastInViews5 = it8.next();
                Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews5, "sharedElementLastInViews");
                View view7 = sharedElementLastInViews5;
                Log.v(str5, "View: " + view7 + " Name: " + ViewCompat.getTransitionName(view7));
            }
        }
        transitionImpl.beginDelayedTransition(getContainer(), mergedTransition2);
        transitionImpl.setNameOverridesReordered(getContainer(), sharedElementFirstOutViews3, sharedElementLastInViews4, inNames, sharedElementNameMapping4);
        FragmentTransition.setViewVisibility(enteringViews3, 0);
        transitionImpl.swapSharedElementTargets(sharedElementTransition2, sharedElementFirstOutViews3, sharedElementLastInViews4);
        return startedTransitions3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation $lastIn, SpecialEffectsController.Operation $firstOut, boolean $isPop, ArrayMap lastInViews) {
        Intrinsics.checkNotNullParameter(lastInViews, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd($lastIn.getFragment(), $firstOut.getFragment(), $isPop, lastInViews, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl impl, View $lastInEpicenterView, Rect lastInEpicenterRect) {
        Intrinsics.checkNotNullParameter(impl, "$impl");
        Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
        impl.getBoundsOnScreen($lastInEpicenterView, lastInEpicenterRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList transitioningViews) {
        Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
        FragmentTransition.setViewVisibility(transitioningViews, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
        }
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, final Collection<String> collection) {
        Set<Map.Entry<String, View>> entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt.retainAll(entries, new Function1<Map.Entry<String, View>, Boolean>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$retainMatchingViews$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry<String, View> entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(CollectionsKt.contains(collection, ViewCompat.getTransitionName(entry.getValue())));
            }
        });
    }

    private final void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
        if (view instanceof ViewGroup) {
            if (ViewGroupCompat.isTransitionGroup((ViewGroup) view)) {
                if (!transitioningViews.contains(view)) {
                    transitioningViews.add(view);
                    return;
                }
                return;
            }
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    captureTransitioningViews(transitioningViews, child);
                }
            }
            return;
        }
        if (!transitioningViews.contains(view)) {
            transitioningViews.add(view);
        }
    }

    private final void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getSignal", "()Landroidx/core/os/CancellationSignal;", "completeSpecialEffect", "", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.operation = operation;
            this.signal = signal;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
            View view = this.operation.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State currentState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return currentState == finalState || !(currentState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean isPop) {
            super(operation, signal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.isPop = isPop;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator it = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = it;
            this.isAnimLoaded = true;
            return it;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0002J\u0006\u0010\u0016\u001a\u00020\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean isPop, boolean providesSharedElementTransition) {
            super(operation, signal);
            Object returnTransition;
            boolean z;
            Object obj;
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                Fragment fragment = operation.getFragment();
                returnTransition = isPop ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = isPop ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (isPop) {
                    z = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z = operation.getFragment().getAllowEnterTransitionOverlap();
                }
            } else {
                z = true;
            }
            this.isOverlapAllowed = z;
            if (providesSharedElementTransition) {
                if (isPop) {
                    obj = operation.getFragment().getSharedElementReturnTransition();
                } else {
                    obj = operation.getFragment().getSharedElementEnterTransition();
                }
            } else {
                obj = null;
            }
            this.sharedElementTransition = obj;
        }

        public final Object getTransition() {
            return this.transition;
        }

        /* renamed from: isOverlapAllowed, reason: from getter */
        public final boolean getIsOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl transitionImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl sharedElementTransitionImpl = getHandlingImpl(this.sharedElementTransition);
            if (transitionImpl == null || sharedElementTransitionImpl == null || transitionImpl == sharedElementTransitionImpl) {
                return transitionImpl == null ? sharedElementTransitionImpl : transitionImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
