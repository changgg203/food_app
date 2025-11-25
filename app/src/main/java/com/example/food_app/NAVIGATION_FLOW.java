/**
 * FLOW DIAGRAM: Kết nối các màn ứng dụng Food Delivery
 * =====================================================
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                    AUTHENTICATION FLOW                          │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * 1. LOGIN FLOW
 *    ┌──────────────┐
 *    │ LoginActivity│ (activity_login.xml)
 *    └──────┬───────┘
 *           │
 *    ┌──────v─────────┐
 *    │ Input: Email,  │
 *    │ Password       │
 *    └──────┬─────────┘
 *           │
 *           ├─ Success → MainActivity (3-tab navigation)
 *           │
 *           └─ tvForgotPassword click → ForgotPasswordActivity
 *              Or
 *              btnRegister click → RegisterActivity
 * 
 * 2. REGISTER FLOW
 *    ┌────────────────┐
 *    │RegisterActivity│ (activity_register.xml)
 *    └────────┬───────┘
 *             │
 *      ┌──────v──────────┐
 *      │Input: Name,     │
 *      │Email, Password  │
 *      └──────┬──────────┘
 *             │
 *             └─ Success/Back → LoginActivity
 * 
 * 3. FORGOT PASSWORD FLOW
 *    ┌──────────────────────┐
 *    │ForgotPasswordActivity│ (activity_forgot_password.xml)
 *    └──────────┬───────────┘
 *               │
 *        ┌──────v──────────┐
 *        │Input: Email     │
 *        └──────┬──────────┘
 *               │
 *               ├─ Check Email & Send OTP
 *               │
 *               └─→ VerifyOtpActivity (activity_verify_otp.xml)
 *                    │
 *                    ├─ Input: 5-digit OTP
 *                    │
 *                    └─ Success → ResetPasswordActivity
 * 
 * 4. RESET PASSWORD FLOW
 *    ┌──────────────────────┐
 *    │ResetPasswordActivity │ (activity_reset_password.xml)
 *    └──────────┬───────────┘
 *               │
 *        ┌──────v──────────────┐
 *        │Input: New Password,  │
 *        │Confirm Password      │
 *        └──────┬───────────────┘
 *               │
 *               └─ Success → LoginActivity
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                    MAIN APP FLOW                                │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * 5. MAIN APPLICATION (Sau đăng nhập thành công)
 *    ┌──────────────────┐
 *    │ MainActivity     │ (activity_main.xml)
 *    │ (Nav Component)  │
 *    └────────┬─────────┘
 *             │
 *             ├─ Tab 1: HomeFragment (fragment_home.xml)
 *             │           │
 *             │           ├─ Display: RecyclerView các món ăn
 *             │           │
 *             │           └─ Click Food Item
 *             │               │
 *             │               └─→ FoodDetailActivity (activity_food_detail.xml)
 *             │                    │
 *             │                    ├─ Display: Chi tiết sản phẩm
 *             │                    ├─ Button: +/- (tăng giảm số lượng)
 *             │                    ├─ Button: Yêu thích ❤️
 *             │                    └─ Button: Thêm vào giỏ
 *             │                        │
 *             │                        └─ Back → HomeFragment
 *             │
 *             ├─ Tab 2: DashboardFragment (fragment_dashboard.xml)
 *             │           │
 *             │           └─ Display: RecyclerView các đơn hàng
 *             │               │
 *             │               └─ Status: "Đang chuẩn bị", "Đang giao", "Đã giao"
 *             │
 *             ├─ Tab 3: NotificationsFragment (fragment_notifications.xml)
 *             │           │
 *             │           └─ Display: RecyclerView thông báo
 *             │
 *             └─ Tab 4: ProfileFragment (chưa tạo)
 *                         │
 *                         └─ User Info, Settings, Logout
 *
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                  NAVIGATION STRUCTURE                           │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * Entry Point: LoginActivity (LAUNCHER)
 *   └─→ Đăng nhập thành công
 *       └─→ MainActivity (với 4 bottom navigation tabs)
 *           ├─ Home (icon: grid)
 *           ├─ Dashboard (icon: menu)  
 *           ├─ Notifications (icon: note)
 *           └─ Profile (icon: user) [TODO]
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                   DATA FLOW (MVVM)                              │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * HomeViewModel (MutableLiveData<List<Food>>)
 *   │
 *   └─→ HomeFragment (observe)
 *       │
 *       └─→ FoodAdapter (bind RecyclerView)
 *           │
 *           └─→ On Item Click → FoodDetailActivity (Intent + extras)
 * 
 * DashboardViewModel (MutableLiveData<List<Order>>)
 *   │
 *   └─→ DashboardFragment (observe)
 *       │
 *       └─→ OrderAdapter (bind RecyclerView)
 * 
 * NotificationsViewModel (MutableLiveData<List<Notification>>)
 *   │
 *   └─→ NotificationsFragment (observe)
 *       │
 *       └─→ NotificationAdapter (bind RecyclerView)
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                    ACTIVITY LIFECYCLE                           │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * LoginActivity
 * ├─ LAUNCHER in AndroidManifest.xml
 * ├─ Check: isUserLoggedIn() [TODO - SharedPreferences]
 * └─ if not logged in → show LoginActivity
 *    if logged in → go to MainActivity
 * 
 * MainActivity
 * ├─ Check login status (redirect to LoginActivity if not logged in)
 * ├─ Setup NavController + BottomNavigationView
 * ├─ 4 destinations: home, dashboard, notifications, profile
 * └─ Hide ActionBar
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                    TODO ITEMS                                   │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * ⏳ API Integration:
 *    - Login API
 *    - Register API
 *    - Forgot Password API
 *    - OTP Verification API
 *    - Reset Password API
 *    - Food List API
 *    - Order Management API
 * 
 * ⏳ SharedPreferences:
 *    - Save user token after login
 *    - Check login status in MainActivity
 *    - Save favorite foods
 *    - Save user preferences
 * 
 * ⏳ ProfileFragment:
 *    - Display user info
 *    - Edit profile
 *    - Change password
 *    - Logout button
 * 
 * ⏳ Cart System:
 *    - Add to cart from FoodDetailActivity
 *    - Display cart items
 *    - Checkout flow
 * 
 * ⏳ Advanced Features:
 *    - Search functionality
 *    - Filter by category
 *    - Favorite foods management
 *    - Order tracking
 *    - Payment integration
 * 
 * ┌─────────────────────────────────────────────────────────────────┐
 * │                 MÀNING MENU (bottom_menu.xml)                   │
 * └─────────────────────────────────────────────────────────────────┘
 * 
 * Menu Items:
 * ├─ navigation_home (icon: ic_grid)
 * ├─ navigation_dashboard (icon: ic_menu)
 * ├─ navigation_notifications (icon: ic_note)
 * └─ navigation_profile (icon: ic_user_profile)
 * 
 */
