import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * The class containing your tests for the {@link Demo} class. Make sure you
 * test all methods in this class (including both
 * {@link Demo#main(String[])} and
 * {@link Demo#isTriangle(double, double, double)}).
 */
public class DemoTest {

    public static void main(String[] args) {
        System.out.println("Running tests...");

        try {
            testIsTriangle_ValidTriangles();
            testIsTriangle_InvalidTriangles();
            testIsTriangle_EdgeCases();
            testMain_ValidInput();
            testMain_InvalidInput();

            System.out.println("All tests completed.");
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
        }
    }

    // Kiểm tra các trường hợp tam giác hợp lệ
    private static void testIsTriangle_ValidTriangles() {
        if (!Demo.isTriangle(3, 4, 5)) {
            throw new AssertionError("Test failed: 3, 4, 5 should form a triangle.");
        }
        if (!Demo.isTriangle(5, 5, 5)) {
            throw new AssertionError("Test failed: 5, 5, 5 should form a triangle.");
        }
        if (!Demo.isTriangle(7, 10, 5)) {
            throw new AssertionError("Test failed: 7, 10, 5 should form a triangle.");
        }
        System.out.println("testIsTriangle_ValidTriangles passed.");
    }

    // Kiểm tra các trường hợp tam giác không hợp lệ
    private static void testIsTriangle_InvalidTriangles() {
        if (Demo.isTriangle(1, 2, 3)) {
            throw new AssertionError("Test failed: 1, 2, 3 should not form a triangle.");
        }
        if (Demo.isTriangle(0, 4, 5)) {
            throw new AssertionError("Test failed: 0, 4, 5 should not form a triangle.");
        }
        if (Demo.isTriangle(-1, 4, 5)) {
            throw new AssertionError("Test failed: -1, 4, 5 should not form a triangle.");
        }
        if (Demo.isTriangle(1, 10, 12)) {
            throw new AssertionError("Test failed: 1, 10, 12 should not form a triangle.");
        }
        System.out.println("testIsTriangle_InvalidTriangles passed.");
    }

    // Kiểm tra các trường hợp biên
    private static void testIsTriangle_EdgeCases() {
        if (Demo.isTriangle(1, 1, 2)) {
            throw new AssertionError("Test failed: 1, 1, 2 should not form a triangle.");
        }
        if (!Demo.isTriangle(1, 1, 1)) {
            throw new AssertionError("Test failed: 1, 1, 1 should form a triangle.");
        }
        if (Demo.isTriangle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0)) {
            throw new AssertionError("Test failed: Max, Max, 0 should not form a triangle.");
        }
        System.out.println("testIsTriangle_EdgeCases passed.");
    }

    // Kiểm tra đầu vào hợp lệ cho hàm main
    private static void testMain_ValidInput() {
        String input = "3\n4\n5\n";
        simulateMainInputOutput(input, "This is a triangle.");
        System.out.println("testMain_ValidInput passed.");
    }

    // Kiểm tra đầu vào không hợp lệ cho hàm main
    private static void testMain_InvalidInput() {
        String input = "1\n2\n3\n";
        simulateMainInputOutput(input, "This is not a triangle.");
        System.out.println("testMain_InvalidInput passed.");
    }

    // Phương thức giả lập đầu vào/đầu ra cho hàm main
    private static void simulateMainInputOutput(String input, String expectedOutput) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            InputStream in = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            System.setIn(in);
            System.setOut(new PrintStream(out));

            Demo.main(new String[] {});

            String actualOutput = out.toString().trim();
            if (!actualOutput.contains(expectedOutput)) {
                throw new AssertionError("Expected output to contain \"" + expectedOutput
                        + "\", but got \"" + actualOutput + "\".");
            }
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

}
