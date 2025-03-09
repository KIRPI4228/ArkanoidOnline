using System;
using UnityEngine;
using UnityEngine.EventSystems;

public class GameButton : MonoBehaviour, IPointerClickHandler, IPointerDownHandler, IPointerUpHandler
{
    public EventHandler ButtonClickHandler;
    public EventHandler ButtonDownkHandler;
    public EventHandler ButtonUpHandler;
    public EventHandler ButtonPressHandler;
    public EventHandler ButtonFixedPressHandler;

    [HideInInspector] public bool IsButtonDown = false;

    public void OnPointerClick(PointerEventData eventData)
        => Invoke(ButtonClickHandler, this, EventArgs.Empty);

    public void OnPointerDown(PointerEventData eventData)
    {
        IsButtonDown = true;
        Invoke(ButtonDownkHandler, this, EventArgs.Empty);
    }

    public void OnPointerUp(PointerEventData eventData)
    {
        IsButtonDown = false;
        Invoke(ButtonUpHandler, this, EventArgs.Empty);
    }

    private void Invoke(EventHandler handler, object sender, EventArgs args)
    {
        if (handler != null)
            handler.Invoke(sender, args);
    }
}
