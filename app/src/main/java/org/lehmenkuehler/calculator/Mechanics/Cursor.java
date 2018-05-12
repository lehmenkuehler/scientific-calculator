package org.lehmenkuehler.calculator.Mechanics;

import android.widget.EditText;
import java.util.List;
import org.lehmenkuehler.calculator.Enums.Component;

public class Cursor
{
    private List<Element> elementList;
    private int cursorPosition = 0;
    private int elementPosition = 0;

    public Cursor()
    {
    }

    public void setElementPositionManually(int pos)
    {
        // only change position if within allowed index range
        if (pos >= 0 && pos < elementList.size()) elementPosition = pos;
        // update real cursor position
        indexToReal();
    }

    public int getElementPosition() {
        return elementPosition;
    }

    public void updateElementList(List<Element> elementList) {
        this.elementList = elementList;
    }


    public void cursorPositionForward()
    {
        if (elementList == null) return;
        if (elementPosition < elementList.size())
        {
            if (Check.isPhantom(elementList.get(elementPosition).getComponent()))
            {
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
                cursorPositionForward();
            } else if (elementPosition + 1 < elementList.size() &&
                    elementList.get(elementPosition + 1).getComponent() == Component.FUNCTION_POW)
            {
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
            } else if (elementPosition + 1 < elementList.size()
                    && elementList.get(elementPosition + 1).getComponent() == Component.OPERATOR_FRACTION)
            {
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
            } else if (elementPosition < elementList.size())
            {
                cursorPosition += elementList.get(elementPosition).getComponent().getLength();
                elementPosition++;
            }
        }
    }

    public void cursorPositionOneForward()
    {
        if (elementPosition < elementList.size())
        {
            cursorPosition += elementList.get(elementPosition).getComponent().getLength();
            elementPosition++;
        }
    }

    public void cursorPositionBackward()
    {
        if (elementPosition > 0)
        {
            cursorPosition -= elementList.get(elementPosition - 1).getComponent().getLength();
            elementPosition--;
            if (elementPosition > 0 && Check.isPhantom(elementList.get(elementPosition - 1).getComponent()))
            {
                cursorPositionBackward();
            } else if (elementList.get(elementPosition).getComponent() == Component.FUNCTION_POW)
            {
                cursorPositionBackward();
            } else if (elementPosition - 1 > 0
                    && elementList.get(elementPosition - 1).getComponent() == Component.OPERATOR_FRACTION)
            {
                cursorPosition -= elementList.get(elementPosition - 1).getComponent().getLength();
                elementPosition--;
                cursorPosition -= elementList.get(elementPosition - 1).getComponent().getLength();
                elementPosition--;
            }
        }
    }

    public void updateCursor(EditText et)
    {
        if (cursorPosition > et.getText().length())
        {
            cursorPosition = et.getText().length();
            elementPosition = elementList.size() - 1;
        }
        et.setSelection(cursorPosition);
    }

    public void synchronizeAfterTouch(int pos)
    {
        cursorPosition = pos;
        realToIndex();
        // set cursor back if it's behind a phantom
        if (elementPosition > 0 && Check.isPhantom(elementList.get(elementPosition - 1).getComponent()))
        {
            cursorPosition -= elementList.get(elementPosition - 1).getComponent().getLength();
            elementPosition--;
        }
    }

    private void indexToReal()
    {
        cursorPosition = 0;
        for (int i = 0; i < elementPosition; i++)
        {
            cursorPosition += elementList.get(i).getComponent().getLength();
        }
    }

    private void realToIndex()
    {
        int fill = 0;
        elementPosition = 0;
        if (elementList.size() == 0) cursorPosition = 0;
        while (fill < cursorPosition && elementPosition < elementList.size())
        {
            fill += elementList.get(elementPosition).getComponent().getLength();
            elementPosition++;
        }
        cursorPosition = fill;
    }
}