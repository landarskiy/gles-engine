package by.vsu.mf.simplegles20.core.util;

/**
 * Вспомогательный класс для простой конвертации из GLES в экранные координаты и
 * наоборот
 * 
 * @author Landarski Yauhen
 * 
 */
public class ScreenHelper {
	/**
	 * Ширина экрана в пикселях
	 */
	private int screenWidth;

	/**
	 * Высота экрана в пикселях.
	 */
	private int screenHeight;

	/**
	 * 
	 * @param screenWidth
	 *            ширина экрана в пикселях
	 * @param screenHeight
	 *            высота экрана в пикселях
	 */
	public ScreenHelper(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	/**
	 * Конвертация x экранных координат в x GLES координат
	 * 
	 * @param screenXValue
	 *            экранная координата x
	 * @return значение в интервале [-1,1] - координата x в системе GLES
	 */
	public float toGLESX(int screenXValue) {
		return (float) screenXValue * 2f / (float) screenWidth - 1f;
	}

	/**
	 * Конвертация y экранных координат в y GLES координат
	 * 
	 * @param screenYValue
	 *            экранная координата x
	 * @return значение в интервале [-1,1] - координата y в системе GLES
	 */
	public float toGLESY(int screenYValue) {
		return (float) - (screenYValue * 2f / (float) screenHeight - 1f);
	}

	/**
	 * Конвертация x GLES координат в x экранных координат
	 * 
	 * @param glesXValue
	 *            GLES координата x
	 * @return значение в интервале [0,{@link ScreenHelper#screenWidth} ] -
	 *         координата x в экранной системе координат
	 */
	public int toScreenX(float glesXValue) {
		return (int) ((glesXValue + 1f) * screenWidth / 2f);
	}

	/**
	 * Конвертация y GLES координат в y экранных координат
	 * 
	 * @param glesYValue
	 *            GLES координата y
	 * @return значение в интервале [0,{@link ScreenHelper#screenHeight} ] -
	 *         координата y в экранной системе координат
	 */
	public int toScreenY(float glesYValue) {
		return (int) -((glesYValue - 1f) * screenHeight / 2f);
	}

	/**
	 * Конвертация ширины текстуры в экранных координатах в ширину GLES координат
	 * 
	 * @param screenWidthValue
	 *            ширина в экранных координатах
	 * @return значение в интервале [0,1] - ширина текстуры в GLES координатах
	 */
	public float toGLESWidth(int screenWidthValue) {
		return (float) screenWidthValue / (float) screenWidth;
	}

	/**
	 * Конвертация высоты текстуры в экранных координатах в высоту GLES координат
	 * 
	 * @param screenHeightValue
	 *            высота в экранных координатах
	 * @return значение в интервале [0,1] - высота текстуры в GLES координатах
	 */
	public float toGLESHeight(int screenHeightValue) {
		return (float) screenHeightValue / (float) screenHeight;
	}

	/**
	 * Конвертация ширины текстуры GLES координат в ширину экранных координат
	 * 
	 * @param glesWidthValue
	 *            ширина в GLES координатах
	 * @return значение в интервале [0,{@link ScreenHelper#screenWidth} ] -
	 *         ширина текстуры в экранных координатах
	 */
	public int toScreenWidth(float glesWidthValue) {
		return (int) (glesWidthValue * screenWidth);
	}

	/**
	 * Конвертация высоты текстуры GLES координат в высоту экранных координат
	 * 
	 * @param glesHeightValue
	 *            высота в GLES координатах
	 * @return значение в интервале [0,{@link ScreenHelper#screenHeight} ] -
	 *         высота текстуры в экранных координатах
	 */
	public int toScreenHeight(float glesHeightValue) {
		return (int) (glesHeightValue * screenHeight);
	}
}
