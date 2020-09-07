public class Mat {
    public static float map(float value, int minValue, int maxValue, int newMin, int newMax) {
        if(value == minValue) return newMin;
        else if(value == maxValue) return newMax;
        else return (value * newMax) / maxValue;
    }

    public static float abs(float value) {
        if(value >= 0) return value;
        else return -value;
    }
}
