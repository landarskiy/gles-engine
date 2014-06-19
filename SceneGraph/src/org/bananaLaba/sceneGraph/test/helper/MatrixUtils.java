package org.bananaLaba.sceneGraph.test.helper;

import com.jogamp.opengl.math.FloatUtil;

// FXIME: write an abstract interface for matrix stack manipulation and use this class below as one of it's
// implementations, i.e.:
// 1) satisfy the abstraction contracts;
// 2) make all methods non-static;
// 3) make the sTemp field non-static and remove synchronized blocks.
public class MatrixUtils {

    private final static float[] sTemp = new float[32];

    public static void setFrustum(final float[] target, final int offset, final float left, final float right,
            final float bottom, final float top, final float near, final float far) {
        if (left == right) {
            throw new IllegalArgumentException("left == right");
        }
        if (top == bottom) {
            throw new IllegalArgumentException("top == bottom");
        }
        if (near == far) {
            throw new IllegalArgumentException("near == far");
        }
        if (near <= 0.0f) {
            throw new IllegalArgumentException("near <= 0.0f");
        }
        if (far <= 0.0f) {
            throw new IllegalArgumentException("far <= 0.0f");
        }

        final float r_width  = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth  = 1.0f / (near - far);
        final float x = 2.0f * (near * r_width);
        final float y = 2.0f * (near * r_height);
        final float A = (right + left) * r_width;
        final float B = (top + bottom) * r_height;
        final float C = (far + near) * r_depth;
        final float D = 2.0f * (far * near * r_depth);

        target[offset] = x;
        target[offset + 5] = y;
        target[offset + 8] = A;
        target[offset + 9] = B;

        target[offset + 10] = C;
        target[offset + 14] = D;
        target[offset + 11] = -1.0f;
        target[offset + 1] = 0.0f;

        target[offset + 2] = 0.0f;
        target[offset + 3] = 0.0f;
        target[offset + 4] = 0.0f;
        target[offset + 6] = 0.0f;

        target[offset + 7] = 0.0f;
        target[offset + 12] = 0.0f;
        target[offset + 13] = 0.0f;
        target[offset + 15] = 0.0f;
    }

    public static void multiplyMatrices(final float[] a, final int offsetA, final float[] b, final int offsetB,
            final float[] target, final int offsetTarget) {
        FloatUtil.multMatrixf(a == target ? a.clone() : a, offsetA,
                b == target ? b.clone() : b, offsetB,
                target, offsetTarget);
    }

    public static void multiplyVectorByMatrix(final float[] m, final int offsetM, final float[] v, final int offsetV,
            final float[] target, final int offsetTarget) {
        FloatUtil.multMatrixVecf(m == target ? m.clone() : m, offsetM,
                v == target ? v.clone() : v, offsetV,
                target, offsetTarget);
    }

    public static void setLookAt(final float[] target, final int offset, final float eyeX, final float eyeY,
            final float eyeZ, final float centerX, final float centerY, final float centerZ, final float upX,
            final float upY, final float upZ) {
        final float[] l = {
          centerX - eyeX,
          centerY - eyeY,
          centerZ - eyeZ
        };
        FloatUtil.normalize(l);

        final float[] u = {
                upX,
                upY,
                upZ
        };
        final float[] s = new float[3];
        FloatUtil.cross(l, u, s);
        FloatUtil.normalize(s);

        final float[] u2 = new float[3];
        FloatUtil.cross(s, l, u2);

        target[offset] = s[0];
        target[offset + 1] = s[1];
        target[offset + 2] = s[2];

        target[offset + 4] = u2[0];
        target[offset + 5] = u2[1];
        target[offset + 6] = u2[2];

        target[offset + 8] = -l[0];
        target[offset + 9] = -l[1];
        target[offset + 10] = -l[2];

        target[offset + 12] = -eyeX;
        target[offset + 13] = -eyeY;
        target[offset + 14] = -eyeZ;
        target[offset + 15] = 1;
    }

    public static void translate(final float[] target, final int offset, final float x, final float y, final float z) {
        for (int i = 0 ; i < 4 ; i++) {
            int mi = offset + i;
            target[12 + mi] += (target[mi] * x) + (target[4 + mi] * y) + (target[8 + mi] * z);
        }
    }

    public static void rotate(final float[] target, final int offset, final float angle, final float x,
            final float y, final float z) {
        synchronized(MatrixUtils.sTemp) {
            MatrixUtils.setRotateM(MatrixUtils.sTemp, 0, angle, x, y, z);
            FloatUtil.multMatrixf(target, offset, MatrixUtils.sTemp, 0, MatrixUtils.sTemp, 16);
            System.arraycopy(MatrixUtils.sTemp, 16, target, offset, 16);
        }
    }

    public static void setRotateM(final float[] rm, final int rmOffset,
            float a, float x, float y, float z) {
        rm[rmOffset + 3] = 0;
        rm[rmOffset + 7] = 0;
        rm[rmOffset + 11] = 0;
        rm[rmOffset + 12] = 0;
        rm[rmOffset + 13] = 0;
        rm[rmOffset + 14] = 0;
        rm[rmOffset + 15] = 1;

        a *= (float) (Math.PI / 180.0f);

        float s = (float) Math.sin(a);
        float c = (float) Math.cos(a);

        if ((1.0f == x) && (0.0f == y) && (0.0f == z)) {
            rm[rmOffset + 5] = c;   rm[rmOffset + 10]= c;
            rm[rmOffset + 6] = s;   rm[rmOffset + 9] = -s;
            rm[rmOffset + 1] = 0;   rm[rmOffset + 2] = 0;
            rm[rmOffset + 4] = 0;   rm[rmOffset + 8] = 0;
            rm[rmOffset + 0] = 1;
        } else if (0.0f == x && 1.0f == y && 0.0f == z) {
            rm[rmOffset + 0] = c;   rm[rmOffset + 10]= c;
            rm[rmOffset + 8] = s;   rm[rmOffset + 2] = -s;
            rm[rmOffset + 1] = 0;   rm[rmOffset + 4] = 0;
            rm[rmOffset + 6] = 0;   rm[rmOffset + 9] = 0;
            rm[rmOffset + 5] = 1;
        } else if (0.0f == x && 0.0f == y && 1.0f == z) {
            rm[rmOffset + 0] = c;   rm[rmOffset + 5] = c;
            rm[rmOffset + 1] = s;   rm[rmOffset + 4] = -s;
            rm[rmOffset + 2] = 0;   rm[rmOffset + 6] = 0;
            rm[rmOffset + 8] = 0;   rm[rmOffset + 9] = 0;
            rm[rmOffset + 10]= 1;
        } else {
            float len = Matrix.length(x, y, z);
            if (1.0f != len) {
                float recipLen = 1.0f / len;
                x *= recipLen;
                y *= recipLen;
                z *= recipLen;
            }

            float nc = 1.0f - c;
            float xy = x * y;
            float yz = y * z;
            float zx = z * x;
            float xs = x * s;
            float ys = y * s;
            float zs = z * s;

            rm[rmOffset +  0] = x*x*nc + c;
            rm[rmOffset +  4] = xy*nc - zs;
            rm[rmOffset +  8] = zx*nc + ys;
            rm[rmOffset +  1] = xy*nc + zs;
            rm[rmOffset +  5] = y*y*nc + c;
            rm[rmOffset +  9] = yz*nc - xs;
            rm[rmOffset +  2] = zx*nc - ys;
            rm[rmOffset +  6] = yz*nc + xs;
            rm[rmOffset + 10] = z*z*nc + c;
        }
    }

}
