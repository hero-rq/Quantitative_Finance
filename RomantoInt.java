
    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = value(s.charAt(i));
            if (i + 1 < s.length() && val < value(s.charAt(i + 1))) {
                result -= val;
            } else {
                result += val;
            }
        }
        return result;
    }

    private static int value(char r) {
        switch (r) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Enter Roman numeral:");
        String input = user_input.nextLine();

        int result = romanToInt(input);
        if (result != -1) {
            System.out.println("Integer value: " + result);
        } else {
            System.out.println("Invalid input");
        }
    }
