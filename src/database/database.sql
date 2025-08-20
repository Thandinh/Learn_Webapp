-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 20, 2025 at 07:08 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `learn_javaweb`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `thumbnail` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `thumbnail`) VALUES
(1, 'Trái cây khô', NULL),
(2, 'Trái cây nhập khẩu', NULL),
(3, 'Trái cây Việt', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `code` varchar(1028) NOT NULL,
  `status` varchar(1028) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `code`, `status`, `user_id`, `created_at`) VALUES
(41, 'ORD-20250705-001', 'completed', 2, '2025-07-05 03:00:00'),
(42, 'ORD-20250707-002', 'processing', 3, '2025-07-07 07:30:00'),
(43, 'ORD-20250710-003', 'completed', 2, '2025-07-10 02:00:00'),
(44, 'ORD-20250712-004', 'processing', 3, '2025-07-12 04:15:00'),
(45, 'ORD-20250715-005', 'cancelled', 2, '2025-07-15 09:45:00'),
(46, 'ORD-20250718-006', 'processing', 3, '2025-07-18 01:30:00'),
(47, 'ORD-20250722-007', 'completed', 2, '2025-07-22 06:00:00'),
(48, 'ORD-20250725-008', 'cancelled', 3, '2025-07-25 03:40:00'),
(49, 'ORD-20250728-009', 'completed', 2, '2025-07-28 10:00:00'),
(50, 'ORD-20250730-010', 'processing', 3, '2025-07-30 02:50:00'),
(51, 'ORD-20250710-001', 'processing', 3, '2025-07-10 07:17:30'),
(52, 'ORD-20250710-052', 'processing', 3, '2025-07-10 07:31:36'),
(53, 'ORD-20250710-053', 'completed', 3, '2025-07-10 07:32:02'),
(54, 'ORD-20250720-054', 'processing', 3, '2025-07-20 16:13:20');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `quantity`, `price`, `order_id`, `product_id`) VALUES
(87, 2, 55000.00, 41, 98),
(88, 1, 40000.00, 41, 102),
(89, 3, 79000.00, 42, 115),
(90, 2, 25000.00, 42, 107),
(91, 1, 355000.00, 43, 103),
(92, 1, 160000.00, 43, 112),
(93, 4, 30000.00, 44, 116),
(94, 1, 55000.00, 44, 108),
(95, 1, 90000.00, 45, 99),
(96, 1, 55000.00, 45, 100),
(97, 1, 240000.00, 46, 110),
(98, 2, 350000.00, 46, 113),
(99, 2, 70000.00, 47, 104),
(100, 3, 31000.00, 47, 114),
(101, 1, 80000.00, 48, 101),
(102, 2, 30000.00, 48, 109),
(103, 1, 235000.00, 49, 106),
(104, 1, 230000.00, 49, 111),
(106, 1, 55000.00, 50, 98),
(107, 2, 55000.00, 51, 100),
(108, 3, 55000.00, 51, 108),
(114, 3, 70000.00, 52, 104),
(115, 2, 355000.00, 52, 103),
(116, 1, 55000.00, 53, 100),
(117, 1, 55000.00, 54, 100),
(118, 3, 40000.00, 54, 102);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `pricesale` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `view` int(11) DEFAULT 0,
  `thumbnail` varchar(1024) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `pricesale`, `quantity`, `view`, `thumbnail`, `category_id`, `created_at`, `updated_at`) VALUES
(98, 'Sầu riêng Miền Nam', 'Nếu bạn tiêu thụ khoảng 234g sầu riêng điều đó tương đương với bạn hấp thụ khoảng 20% carbohydrate cần trong ngày...', 60000.00, 55000.00, 9, 0, 'assets/images/saurieng1-1000x1000.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:51:39'),
(99, 'Bông cải đen', 'Thông tin đang được cập nhật', 190000.00, 90000.00, 0, 0, 'assets/images/bongcaitrang1-1000x1000.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(100, 'Quả thanh long', 'Thông tin sản phẩm đang được cập nhật', 160000.00, 55000.00, 0, 0, 'assets/images/thanhlong1-1000x1000.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(101, 'Măng cụt chín Miền Nam', 'Thông tin đang cập nhật', 165000.00, 80000.00, 0, 0, 'assets/images/mangcut1-1000x1000.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(102, 'Lựu đỏ Nam Phi nhập khẩu', 'Hạt lựu chín có giá trị dinh dưỡng cao... khi ăn không nên nuốt hạt lựu...', 115000.00, 40000.00, 0, 0, 'assets/images/qualuu1-1000x1000.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(103, 'Quả đu đủ', 'Thông tin sản phẩm đang được cập nhật', 665000.00, 355000.00, 2, 0, 'assets/images/dudu1.webp', 1, '2025-06-07 17:34:35', '2025-07-03 05:57:29'),
(104, 'Dứa vàng nhập khẩu Mỹ', 'Là một nguồn tốt của nhiều chất dinh dưỡng... bromelain...', 180000.00, 70000.00, 0, 0, 'assets/images/duavang1.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(105, 'Củ gừng ta', 'Thông tin sản phẩm đang được cập nhật', 160000.00, 60000.00, 0, 0, 'assets/images/cugung1.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(106, 'Cà rốt nhập khẩu', 'Thông tin sản phẩm đang được cập nhật', 265000.00, 235000.00, 0, 0, 'assets/images/carot1.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(107, 'Súp lơ xanh', 'Nguồn gốc: Việt Nam Khối lượng: 1kg...', 30000.00, 25000.00, 0, 0, 'assets/images/suplo1.webp', 1, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(108, 'Hành tây đặc biệt', 'Nguồn gốc: Việt Nam... làm giảm cholesterol.', 60000.00, 55000.00, 0, 0, 'assets/images/hanhtaydacbiet1.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(109, 'Quả na miền bắc', 'Nguồn gốc: Việt Nam... bảo vệ sức khỏe', 45000.00, 30000.00, 0, 0, 'assets/images/quanamienbac1.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(110, 'Rau xanh đặc biệt', 'Thông tin sản phẩm đang được cập nhật', 340000.00, 240000.00, 0, 0, 'assets/images/rauxanhdacbiet1.webp', 1, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(111, 'Khoai tây đỏ', 'Thông tin sản phẩm đang được cập nhật', 260000.00, 230000.00, 0, 0, 'assets/images/khoaitaydo1.jpg', 1, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(112, 'Táo xanh Mỹ', 'Nguồn gốc: Mỹ... Táo có màu xanh lá, vị chua đậm...', 220000.00, 160000.00, 0, 0, 'assets/images/taoxanhmy1.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(113, 'Khoai lang ta', 'Thông tin sản phẩm đang được cập nhật', 480000.00, 350000.00, 0, 0, 'assets/images/khoailang1.jpg', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(114, 'Cà chua nhập khẩu', 'Thông tin sản phẩm đang được cập nhật', 39000.00, 31000.00, 0, 0, 'assets/images/cachua1.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(115, 'Dâu tây Đà Lạt', 'Nguồn gốc: Việt Nam... trái ngon và bổ không thể bỏ qua.', 40000.00, 79000.00, 0, 0, 'assets/images/dautaydalat1.webp', 3, '2025-06-07 17:34:35', '2025-07-02 06:50:01'),
(116, 'Chuối Laba nhập khẩu Thái Lan', 'Chuối là một trong những loại trái cây được tiêu thụ rộng rãi nhất...', 90000.00, 30000.00, 0, 0, 'assets/images/chuoi1.webp', 2, '2025-06-07 17:34:35', '2025-07-02 06:50:01');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'user',
  `avatar` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1: Active, 0: Inactive',
  `created_at` datetime NOT NULL DEFAULT current_timestamp() COMMENT 'Ngày tạo tài khoản'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `role`, `avatar`, `status`, `created_at`) VALUES
(2, 'abc1@gmail.com', '$2a$10$DmXdIJ4Y.3xQcGTa32.3xuqKKHow4hTw73Q7zMCtjlxLPhdfwR9xi', 'admin', 'assets/images/uploads/avatar/1751872637892_test33.jpeg', 1, '2025-07-05 15:00:25'),
(3, 'abc@gmail.com', '$2a$10$CjzKqIGHLZFBLcWvBcIKBeyD/NAZmTUNGXn5nQOj2Dred4knnH8wC', 'user', 'assets/images/uploads/avatar/c7e01229-0eb5-4325-b0c4-afef66feb960_test33.jpeg', 1, '2025-07-05 15:00:25'),
(4, 'abc2@gmail.com', '$2a$10$.FoxEU/MqkmwRPpdzfZkAOb25.BMAI3HHR.c5wgxnaCj2B884WIkO', 'user', 'assets/images/uploads/avatar/default.jpg', 0, '2025-07-05 15:00:25'),
(5, 'abc3@gmail.com', '$2a$10$x1G7St2o//mJQNPz5rOfRuD4DsGUEI.BsjWWUSl.kNisQfURQKUVi', 'admin', 'assets/images/uploads/avatar/default.jpg', 0, '2025-07-05 15:47:51'),
(8, 'admin@gmail.com', '$2a$10$8rAlF4zJvba40nT3xvyOBeu1E4rJlwyyNXD4U7vf2bUQohDxs2eiC', 'admin', 'assets/images/uploads/avatar/32b67d2d-3fac-4545-9dbe-5a214a88c44e_default.jpg', 1, '2025-07-06 14:34:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`) USING HASH,
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
