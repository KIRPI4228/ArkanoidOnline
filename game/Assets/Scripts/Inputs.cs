using System;
using UnityEngine;

namespace Game 
{
    public class Inputs : MonoBehaviour
    {
        public static float GetAxis(KeyCode first, KeyCode second)
        {
            return GetAxis(Input.GetKey(first), Input.GetKey(second));
        }

        public static float GetDownAxis(KeyCode first, KeyCode second)
        {
            return GetAxis(Input.GetKeyDown(first), Input.GetKeyDown(second));
        }

        public static float GetUpAxis(KeyCode first, KeyCode second)
        {
            return GetAxis(Input.GetKeyUp(first), Input.GetKeyUp(second));
        }

        public static float GetAxis(bool first, bool second)
        {
            return -Convert.ToInt32(first && !second) + Convert.ToInt32(second && !first);
            //return -OptimizationHelper.ConvertBoolToInt(first && !second) + OptimizationHelper.ConvertBoolToInt(second && !first);
        }
    }
}
