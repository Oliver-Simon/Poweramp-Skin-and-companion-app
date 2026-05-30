package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: classes.dex */
public class CLParser {
    static boolean DEBUG = false;
    private boolean hasComment = false;
    private int lineNumber;
    private String mContent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum TYPE {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN
    }

    public static CLObject parse(String string) throws CLParsingException {
        return new CLParser(string).parse();
    }

    public CLParser(String content) {
        this.mContent = content;
    }

    public CLObject parse() throws CLParsingException {
        int i;
        int startIndex;
        char ck;
        char[] content = this.mContent.toCharArray();
        int length = content.length;
        int i2 = 1;
        this.lineNumber = 1;
        int startIndex2 = -1;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            char c = content[i3];
            if (c == '{') {
                startIndex2 = i3;
                break;
            }
            if (c == '\n') {
                this.lineNumber++;
            }
            i3++;
        }
        if (startIndex2 == -1) {
            throw new CLParsingException("invalid json content", null);
        }
        CLObject root = CLObject.allocate(content);
        root.setLine(this.lineNumber);
        root.setStart(startIndex2);
        CLElement currentElement = root;
        int i4 = startIndex2 + 1;
        while (true) {
            if (i4 >= length) {
                i = 1;
                break;
            }
            char c2 = content[i4];
            if (c2 == '\n') {
                this.lineNumber += i2;
            }
            if (this.hasComment) {
                if (c2 == '\n') {
                    this.hasComment = false;
                } else {
                    startIndex = startIndex2;
                    i4++;
                    startIndex2 = startIndex;
                    i2 = 1;
                }
            }
            if (currentElement == null) {
                i = 1;
                break;
            }
            if (currentElement.isDone()) {
                currentElement = getNextJsonElement(i4, c2, currentElement, content);
                startIndex = startIndex2;
            } else if (currentElement instanceof CLObject) {
                if (c2 == '}') {
                    currentElement.setEnd(i4 - 1);
                    startIndex = startIndex2;
                } else {
                    currentElement = getNextJsonElement(i4, c2, currentElement, content);
                    startIndex = startIndex2;
                }
            } else if (currentElement instanceof CLArray) {
                if (c2 == ']') {
                    currentElement.setEnd(i4 - 1);
                    startIndex = startIndex2;
                } else {
                    currentElement = getNextJsonElement(i4, c2, currentElement, content);
                    startIndex = startIndex2;
                }
            } else if (currentElement instanceof CLString) {
                if (content[(int) currentElement.start] == c2) {
                    currentElement.setStart(currentElement.start + 1);
                    currentElement.setEnd(i4 - 1);
                }
                startIndex = startIndex2;
            } else {
                if (!(currentElement instanceof CLToken)) {
                    startIndex = startIndex2;
                } else {
                    CLToken token = (CLToken) currentElement;
                    startIndex = startIndex2;
                    if (!token.validate(c2, i4)) {
                        throw new CLParsingException("parsing incorrect token " + token.content() + " at line " + this.lineNumber, token);
                    }
                }
                if (((currentElement instanceof CLKey) || (currentElement instanceof CLString)) && (((ck = content[(int) currentElement.start]) == '\'' || ck == '\"') && ck == c2)) {
                    currentElement.setStart(currentElement.start + 1);
                    currentElement.setEnd(i4 - 1);
                }
                if (!currentElement.isDone() && (c2 == '}' || c2 == ']' || c2 == ',' || c2 == ' ' || c2 == '\t' || c2 == '\r' || c2 == '\n' || c2 == ':')) {
                    currentElement.setEnd(i4 - 1);
                    if (c2 == '}' || c2 == ']') {
                        currentElement = currentElement.getContainer();
                        currentElement.setEnd(i4 - 1);
                        if (currentElement instanceof CLKey) {
                            currentElement = currentElement.getContainer();
                            currentElement.setEnd(i4 - 1);
                        }
                    }
                }
            }
            if (currentElement.isDone() && (!(currentElement instanceof CLKey) || ((CLKey) currentElement).mElements.size() > 0)) {
                currentElement = currentElement.getContainer();
            }
            i4++;
            startIndex2 = startIndex;
            i2 = 1;
        }
        while (currentElement != null && !currentElement.isDone()) {
            if (currentElement instanceof CLString) {
                currentElement.setStart(((int) currentElement.start) + i);
            }
            currentElement.setEnd(length - 1);
            currentElement = currentElement.getContainer();
        }
        if (DEBUG) {
            System.out.println("Root: " + root.toJSON());
        }
        return root;
    }

    private CLElement getNextJsonElement(int position, char c, CLElement currentElement, char[] content) throws CLParsingException {
        CLElement currentElement2;
        switch (c) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
            case ',':
            case ':':
                currentElement2 = currentElement;
                break;
            case '\"':
            case '\'':
                return currentElement instanceof CLObject ? createElement(currentElement, position, TYPE.KEY, true, content) : createElement(currentElement, position, TYPE.STRING, true, content);
            case '+':
            case '-':
            case '.':
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
            case '2':
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
            case '8':
            case '9':
                return createElement(currentElement, position, TYPE.NUMBER, true, content);
            case '/':
                currentElement2 = currentElement;
                if (position + 1 < content.length && content[position + 1] == '/') {
                    this.hasComment = true;
                    break;
                }
                break;
            case '[':
                return createElement(currentElement, position, TYPE.ARRAY, true, content);
            case ']':
            case '}':
                currentElement.setEnd(position - 1);
                CLElement currentElement3 = currentElement.getContainer();
                currentElement3.setEnd(position);
                return currentElement3;
            case '{':
                return createElement(currentElement, position, TYPE.OBJECT, true, content);
            default:
                if (!(currentElement instanceof CLContainer) || (currentElement instanceof CLObject)) {
                    return createElement(currentElement, position, TYPE.KEY, true, content);
                }
                CLElement currentElement4 = createElement(currentElement, position, TYPE.TOKEN, true, content);
                CLToken token = (CLToken) currentElement4;
                if (!token.validate(c, position)) {
                    throw new CLParsingException("incorrect token <" + c + "> at line " + this.lineNumber, token);
                }
                return currentElement4;
        }
        return currentElement2;
    }

    private CLElement createElement(CLElement currentElement, int position, TYPE type, boolean applyStart, char[] content) {
        CLElement newElement = null;
        if (DEBUG) {
            System.out.println("CREATE " + type + " at " + content[position]);
        }
        switch (type) {
            case OBJECT:
                newElement = CLObject.allocate(content);
                position++;
                break;
            case ARRAY:
                newElement = CLArray.allocate(content);
                position++;
                break;
            case STRING:
                newElement = CLString.allocate(content);
                break;
            case NUMBER:
                newElement = CLNumber.allocate(content);
                break;
            case KEY:
                newElement = CLKey.allocate(content);
                break;
            case TOKEN:
                newElement = CLToken.allocate(content);
                break;
        }
        if (newElement == null) {
            return null;
        }
        newElement.setLine(this.lineNumber);
        if (applyStart) {
            newElement.setStart(position);
        }
        if (currentElement instanceof CLContainer) {
            CLContainer container = (CLContainer) currentElement;
            newElement.setContainer(container);
        }
        return newElement;
    }
}
