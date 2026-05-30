package io.ktor.http;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.date.GMTDateParser;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Codecs.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0002\u001a\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eH\u0002\u001a8\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cH\u0002\u001a0\u0010\u001d\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cH\u0002\u001a,\u0010\u001e\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a6\u0010\u001f\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a\n\u0010 \u001a\u00020\u0013*\u00020\u0013\u001a\u0014\u0010!\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\"\u001a\u00020\u0019\u001a\f\u0010#\u001a\u00020\u0013*\u00020\u0013H\u0000\u001a\n\u0010$\u001a\u00020\u0013*\u00020\u0013\u001a\u0014\u0010$\u001a\u00020\u0013*\u00020\u00132\u0006\u0010%\u001a\u00020\u0019H\u0000\u001a\n\u0010&\u001a\u00020\u0013*\u00020\u0013\u001a,\u0010'\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010(\u001a\u00020\u00192\b\b\u0002\u0010\"\u001a\u00020\u00192\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001c\u001a \u0010)\u001a\u00020**\u00020+2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020*0-H\u0002\u001a\f\u0010.\u001a\u00020\u0013*\u00020\bH\u0002\u001a\u001a\u0010.\u001a\u00020\u0013*\u00020\u00132\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"ATTRIBUTE_CHARACTERS", "", "", "getATTRIBUTE_CHARACTERS", "()Ljava/util/Set;", "HEX_ALPHABET", "SPECIAL_SYMBOLS", "", "", "URL_ALPHABET", "URL_ALPHABET_CHARS", "URL_PROTOCOL_PART", "VALID_PATH_PART", "charToHexDigit", "", "c2", "hexDigitToChar", "digit", "decodeImpl", "", "", "start", "end", "prefixEnd", "plusIsSpace", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "decodeScan", "decodeURLPart", "decodeURLQueryComponent", "encodeOAuth", "encodeURLParameter", "spaceToPlus", "encodeURLParameterValue", "encodeURLPath", "encodeSlash", "encodeURLPathPart", "encodeURLQueryComponent", "encodeFull", "forEach", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "block", "Lkotlin/Function1;", "percentEncode", "allowedSet", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CodecsKt {
    private static final Set<Character> ATTRIBUTE_CHARACTERS;
    private static final Set<Character> HEX_ALPHABET;
    private static final List<Byte> SPECIAL_SYMBOLS;
    private static final Set<Byte> URL_ALPHABET;
    private static final Set<Character> URL_ALPHABET_CHARS;
    private static final List<Byte> URL_PROTOCOL_PART;
    private static final Set<Character> VALID_PATH_PART;

    static {
        List plus = CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', GMTDateParser.ZONE), (Iterable) new CharRange('A', 'Z')), (Iterable) new CharRange('0', '9'));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        Iterator it = plus.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) ((Character) it.next()).charValue()));
        }
        URL_ALPHABET = CollectionsKt.toSet(arrayList);
        URL_ALPHABET_CHARS = CollectionsKt.toSet(CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', GMTDateParser.ZONE), (Iterable) new CharRange('A', 'Z')), (Iterable) new CharRange('0', '9')));
        HEX_ALPHABET = CollectionsKt.toSet(CollectionsKt.plus((Collection) CollectionsKt.plus((Iterable) new CharRange('a', 'f'), (Iterable) new CharRange('A', 'F')), (Iterable) new CharRange('0', '9')));
        boolean z = true;
        Set of = SetsKt.setOf((Object[]) new Character[]{Character.valueOf(AbstractJsonLexerKt.COLON), '/', '?', '#', Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), '@', '!', Character.valueOf(Typography.dollar), Character.valueOf(Typography.amp), '\'', '(', ')', Character.valueOf(GMTDateParser.ANY), Character.valueOf(AbstractJsonLexerKt.COMMA), ';', '=', '-', '.', '_', '~', '+'});
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(of, 10));
        Iterator it2 = of.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Byte.valueOf((byte) ((Character) it2.next()).charValue()));
            z = z;
        }
        boolean z2 = z;
        URL_PROTOCOL_PART = arrayList2;
        Character[] chArr = new Character[17];
        chArr[0] = Character.valueOf(AbstractJsonLexerKt.COLON);
        chArr[z2 ? 1 : 0] = '@';
        chArr[2] = '!';
        chArr[3] = Character.valueOf(Typography.dollar);
        chArr[4] = Character.valueOf(Typography.amp);
        chArr[5] = '\'';
        chArr[6] = '(';
        chArr[7] = ')';
        chArr[8] = Character.valueOf(GMTDateParser.ANY);
        chArr[9] = '+';
        chArr[10] = Character.valueOf(AbstractJsonLexerKt.COMMA);
        chArr[11] = ';';
        chArr[12] = '=';
        chArr[13] = '-';
        chArr[14] = '.';
        chArr[15] = '_';
        chArr[16] = '~';
        VALID_PATH_PART = SetsKt.setOf((Object[]) chArr);
        Set<Character> set = URL_ALPHABET_CHARS;
        Character[] chArr2 = new Character[12];
        chArr2[0] = '!';
        chArr2[z2 ? 1 : 0] = '#';
        chArr2[2] = Character.valueOf(Typography.dollar);
        chArr2[3] = Character.valueOf(Typography.amp);
        chArr2[4] = '+';
        chArr2[5] = '-';
        chArr2[6] = '.';
        chArr2[7] = '^';
        chArr2[8] = '_';
        chArr2[9] = '`';
        chArr2[10] = '|';
        chArr2[11] = '~';
        ATTRIBUTE_CHARACTERS = SetsKt.plus((Set) set, (Iterable) SetsKt.setOf((Object[]) chArr2));
        Character[] chArr3 = new Character[4];
        chArr3[0] = '-';
        chArr3[z2 ? 1 : 0] = '.';
        chArr3[2] = '_';
        chArr3[3] = '~';
        List listOf = CollectionsKt.listOf((Object[]) chArr3);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
        Iterator it3 = listOf.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Byte.valueOf((byte) ((Character) it3.next()).charValue()));
        }
        SPECIAL_SYMBOLS = arrayList3;
    }

    public static final Set<Character> getATTRIBUTE_CHARACTERS() {
        return ATTRIBUTE_CHARACTERS;
    }

    public static /* synthetic */ String encodeURLQueryComponent$default(String str, boolean z, boolean z2, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return encodeURLQueryComponent(str, z, z2, charset);
    }

    public static final String encodeURLQueryComponent(String $this$encodeURLQueryComponent, final boolean encodeFull, final boolean spaceToPlus, Charset charset) {
        Intrinsics.checkNotNullParameter($this$encodeURLQueryComponent, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        final StringBuilder $this$encodeURLQueryComponent_u24lambda_u243 = new StringBuilder();
        CharsetEncoder newEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
        ByteReadPacket content = EncodingKt.encode$default(newEncoder, $this$encodeURLQueryComponent, 0, 0, 6, null);
        forEach(content, new Function1<Byte, Unit>() { // from class: io.ktor.http.CodecsKt$encodeURLQueryComponent$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Byte b) {
                invoke(b.byteValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
            
                if (r0.contains(java.lang.Byte.valueOf(r3)) != false) goto L16;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(byte r3) {
                /*
                    r2 = this;
                    r0 = 32
                    if (r3 != r0) goto L19
                    boolean r0 = r2
                    if (r0 == 0) goto L11
                    java.lang.StringBuilder r0 = r3
                    r1 = 43
                    r0.append(r1)
                    goto L4a
                L11:
                    java.lang.StringBuilder r0 = r3
                    java.lang.String r1 = "%20"
                    r0.append(r1)
                    goto L4a
                L19:
                    java.util.Set r0 = io.ktor.http.CodecsKt.access$getURL_ALPHABET$p()
                    java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
                    boolean r0 = r0.contains(r1)
                    if (r0 != 0) goto L44
                    boolean r0 = r4
                    if (r0 != 0) goto L3a
                    java.util.List r0 = io.ktor.http.CodecsKt.access$getURL_PROTOCOL_PART$p()
                    java.lang.Byte r1 = java.lang.Byte.valueOf(r3)
                    boolean r0 = r0.contains(r1)
                    if (r0 == 0) goto L3a
                    goto L44
                L3a:
                    java.lang.StringBuilder r0 = r3
                    java.lang.String r1 = io.ktor.http.CodecsKt.access$percentEncode(r3)
                    r0.append(r1)
                    goto L4a
                L44:
                    java.lang.StringBuilder r0 = r3
                    char r1 = (char) r3
                    r0.append(r1)
                L4a:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CodecsKt$encodeURLQueryComponent$1$1.invoke(byte):void");
            }
        });
        String sb = $this$encodeURLQueryComponent_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String encodeURLPath(String $this$encodeURLPath) {
        Intrinsics.checkNotNullParameter($this$encodeURLPath, "<this>");
        return encodeURLPath($this$encodeURLPath, false);
    }

    public static final String encodeURLPathPart(String $this$encodeURLPathPart) {
        Intrinsics.checkNotNullParameter($this$encodeURLPathPart, "<this>");
        return encodeURLPath($this$encodeURLPathPart, true);
    }

    public static final String encodeURLPath(String $this$encodeURLPath, boolean encodeSlash) {
        Intrinsics.checkNotNullParameter($this$encodeURLPath, "<this>");
        final StringBuilder $this$encodeURLPath_u24lambda_u244 = new StringBuilder();
        Charset charset = Charsets.UTF_8;
        int index = 0;
        while (index < $this$encodeURLPath.length()) {
            char current = $this$encodeURLPath.charAt(index);
            if ((!encodeSlash && current == '/') || URL_ALPHABET_CHARS.contains(Character.valueOf(current)) || VALID_PATH_PART.contains(Character.valueOf(current))) {
                $this$encodeURLPath_u24lambda_u244.append(current);
                index++;
            } else if (current == '%' && index + 2 < $this$encodeURLPath.length() && HEX_ALPHABET.contains(Character.valueOf($this$encodeURLPath.charAt(index + 1))) && HEX_ALPHABET.contains(Character.valueOf($this$encodeURLPath.charAt(index + 2)))) {
                $this$encodeURLPath_u24lambda_u244.append(current);
                $this$encodeURLPath_u24lambda_u244.append($this$encodeURLPath.charAt(index + 1));
                $this$encodeURLPath_u24lambda_u244.append($this$encodeURLPath.charAt(index + 2));
                index += 3;
            } else {
                int symbolSize = CharsKt.isSurrogate(current) ? 2 : 1;
                CharsetEncoder newEncoder = charset.newEncoder();
                Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
                forEach(EncodingKt.encode(newEncoder, $this$encodeURLPath, index, index + symbolSize), new Function1<Byte, Unit>() { // from class: io.ktor.http.CodecsKt$encodeURLPath$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Byte b) {
                        invoke(b.byteValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(byte it) {
                        String percentEncode;
                        StringBuilder sb = $this$encodeURLPath_u24lambda_u244;
                        percentEncode = CodecsKt.percentEncode(it);
                        sb.append(percentEncode);
                    }
                });
                index += symbolSize;
            }
        }
        String sb = $this$encodeURLPath_u24lambda_u244.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String encodeOAuth(String $this$encodeOAuth) {
        Intrinsics.checkNotNullParameter($this$encodeOAuth, "<this>");
        return encodeURLParameter$default($this$encodeOAuth, false, 1, null);
    }

    public static /* synthetic */ String encodeURLParameter$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return encodeURLParameter(str, z);
    }

    public static final String encodeURLParameter(String $this$encodeURLParameter, final boolean spaceToPlus) {
        Intrinsics.checkNotNullParameter($this$encodeURLParameter, "<this>");
        final StringBuilder $this$encodeURLParameter_u24lambda_u245 = new StringBuilder();
        CharsetEncoder newEncoder = Charsets.UTF_8.newEncoder();
        Intrinsics.checkNotNullExpressionValue(newEncoder, "UTF_8.newEncoder()");
        ByteReadPacket content = EncodingKt.encode$default(newEncoder, $this$encodeURLParameter, 0, 0, 6, null);
        forEach(content, new Function1<Byte, Unit>() { // from class: io.ktor.http.CodecsKt$encodeURLParameter$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Byte b) {
                invoke(b.byteValue());
                return Unit.INSTANCE;
            }

            public final void invoke(byte it) {
                Set set;
                List list;
                String percentEncode;
                set = CodecsKt.URL_ALPHABET;
                if (!set.contains(Byte.valueOf(it))) {
                    list = CodecsKt.SPECIAL_SYMBOLS;
                    if (!list.contains(Byte.valueOf(it))) {
                        if (!spaceToPlus || it != 32) {
                            StringBuilder sb = $this$encodeURLParameter_u24lambda_u245;
                            percentEncode = CodecsKt.percentEncode(it);
                            sb.append(percentEncode);
                            return;
                        }
                        $this$encodeURLParameter_u24lambda_u245.append('+');
                        return;
                    }
                }
                $this$encodeURLParameter_u24lambda_u245.append((char) it);
            }
        });
        String sb = $this$encodeURLParameter_u24lambda_u245.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String percentEncode(String $this$percentEncode, Set<Character> allowedSet) {
        byte[] content;
        Intrinsics.checkNotNullParameter($this$percentEncode, "<this>");
        Intrinsics.checkNotNullParameter(allowedSet, "allowedSet");
        String $this$count$iv = $this$percentEncode;
        int count$iv = 0;
        for (int i = 0; i < $this$count$iv.length(); i++) {
            if (!allowedSet.contains(Character.valueOf($this$count$iv.charAt(i)))) {
                count$iv++;
            }
        }
        if (count$iv == 0) {
            return $this$percentEncode;
        }
        Charset charset$iv = Charsets.UTF_8;
        if (Intrinsics.areEqual(charset$iv, Charsets.UTF_8)) {
            content = StringsKt.encodeToByteArray($this$percentEncode);
        } else {
            CharsetEncoder newEncoder = charset$iv.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            content = CharsetJVMKt.encodeToByteArray(newEncoder, $this$percentEncode, 0, $this$percentEncode.length());
        }
        int rawCount = $this$percentEncode.length() - count$iv;
        int resultSize = ((content.length - rawCount) * 3) + rawCount;
        char[] result = new char[resultSize];
        int writeIndex = 0;
        byte[] $this$forEach$iv = content;
        for (byte element$iv : $this$forEach$iv) {
            char c = (char) element$iv;
            if (allowedSet.contains(Character.valueOf(c))) {
                result[writeIndex] = c;
                writeIndex++;
            } else {
                int writeIndex2 = element$iv & 255;
                int writeIndex3 = writeIndex + 1;
                result[writeIndex] = '%';
                int writeIndex4 = writeIndex3 + 1;
                result[writeIndex3] = hexDigitToChar(writeIndex2 >> 4);
                result[writeIndex4] = hexDigitToChar(writeIndex2 & 15);
                writeIndex = writeIndex4 + 1;
            }
        }
        return StringsKt.concatToString(result);
    }

    public static final String encodeURLParameterValue(String $this$encodeURLParameterValue) {
        Intrinsics.checkNotNullParameter($this$encodeURLParameterValue, "<this>");
        return encodeURLParameter($this$encodeURLParameterValue, true);
    }

    public static /* synthetic */ String decodeURLQueryComponent$default(String str, int i, int i2, boolean z, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLQueryComponent(str, i, i2, z, charset);
    }

    public static final String decodeURLQueryComponent(String $this$decodeURLQueryComponent, int start, int end, boolean plusIsSpace, Charset charset) {
        Intrinsics.checkNotNullParameter($this$decodeURLQueryComponent, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan($this$decodeURLQueryComponent, start, end, plusIsSpace, charset);
    }

    public static /* synthetic */ String decodeURLPart$default(String str, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeURLPart(str, i, i2, charset);
    }

    public static final String decodeURLPart(String $this$decodeURLPart, int start, int end, Charset charset) {
        Intrinsics.checkNotNullParameter($this$decodeURLPart, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return decodeScan($this$decodeURLPart, start, end, false, charset);
    }

    private static final String decodeScan(String $this$decodeScan, int start, int end, boolean plusIsSpace, Charset charset) {
        for (int index = start; index < end; index++) {
            char ch = $this$decodeScan.charAt(index);
            if (ch == '%' || (plusIsSpace && ch == '+')) {
                return decodeImpl($this$decodeScan, start, end, index, plusIsSpace, charset);
            }
        }
        if (start == 0 && end == $this$decodeScan.length()) {
            return $this$decodeScan.toString();
        }
        String substring = $this$decodeScan.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    private static final String decodeImpl(CharSequence $this$decodeImpl, int start, int end, int prefixEnd, boolean plusIsSpace, Charset charset) {
        int length = end - start;
        int sbSize = length > 255 ? length / 3 : length;
        StringBuilder sb = new StringBuilder(sbSize);
        if (prefixEnd > start) {
            sb.append($this$decodeImpl, start, prefixEnd);
        }
        int index = prefixEnd;
        byte[] bytes = null;
        while (index < end) {
            char c = $this$decodeImpl.charAt(index);
            if (plusIsSpace && c == '+') {
                sb.append(' ');
                index++;
            } else if (c == '%') {
                if (bytes == null) {
                    bytes = new byte[(end - index) / 3];
                }
                int count = 0;
                while (index < end && $this$decodeImpl.charAt(index) == '%') {
                    if (index + 2 < end) {
                        int digit1 = charToHexDigit($this$decodeImpl.charAt(index + 1));
                        int digit2 = charToHexDigit($this$decodeImpl.charAt(index + 2));
                        if (digit1 == -1 || digit2 == -1) {
                            throw new URLDecodeException("Wrong HEX escape: %" + $this$decodeImpl.charAt(index + 1) + $this$decodeImpl.charAt(index + 2) + ", in " + ((Object) $this$decodeImpl) + ", at " + index);
                        }
                        bytes[count] = (byte) ((digit1 * 16) + digit2);
                        index += 3;
                        count++;
                    } else {
                        throw new URLDecodeException("Incomplete trailing HEX escape: " + $this$decodeImpl.subSequence(index, $this$decodeImpl.length()).toString() + ", in " + ((Object) $this$decodeImpl) + " at " + index);
                    }
                }
                sb.append(new String(bytes, 0, count, charset));
            } else {
                sb.append(c);
                index++;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    public static final String percentEncode(byte $this$percentEncode) {
        int code = $this$percentEncode & 255;
        char[] array = {'%', hexDigitToChar(code >> 4), hexDigitToChar(code & 15)};
        return StringsKt.concatToString(array);
    }

    private static final int charToHexDigit(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('A' <= c2 && c2 < 'G') {
            return (c2 - 'A') + 10;
        }
        if ('a' <= c2 && c2 < 'g') {
            return (c2 - 'a') + 10;
        }
        return -1;
    }

    private static final char hexDigitToChar(int digit) {
        boolean z = false;
        if (digit >= 0 && digit < 10) {
            z = true;
        }
        return z ? (char) (digit + 48) : (char) (((char) (digit + 65)) - '\n');
    }

    private static final void forEach(ByteReadPacket $this$forEach, Function1<? super Byte, Unit> function1) {
        ByteReadPacket $this$takeWhile$iv = $this$forEach;
        boolean release$iv = true;
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$takeWhile$iv, 1);
        if (current$iv == null) {
            return;
        }
        while (true) {
            try {
                Buffer buffer = current$iv;
                while (true) {
                    if (!(buffer.getWritePosition() > buffer.getReadPosition())) {
                        break;
                    } else {
                        function1.invoke(Byte.valueOf(buffer.readByte()));
                    }
                }
                release$iv = false;
                ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$takeWhile$iv, current$iv);
                if (next$iv == null) {
                    break;
                }
                current$iv = next$iv;
                release$iv = true;
            } finally {
                if (release$iv) {
                    UnsafeKt.completeReadHead($this$takeWhile$iv, current$iv);
                }
            }
        }
    }
}
