-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2024 at 05:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Database for Foodie Spot Application
CREATE DATABASE IF NOT EXISTS `foodie_spot`;
USE `foodie_spot`;

-- --------------------------------------------------------

-- Table: Menu Items
-- This table stores the list of menu items offered by the restaurant.

CREATE TABLE `menuitems` (
  `id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Sample Menu Items
INSERT INTO `menuitems` (`id`, `item_name`, `description`, `price`) VALUES
(1, 'Margherita Pizza', 'Classic cheese and tomato pizza made with fresh ingredients.', 299.99),
(2, 'Chicken Biryani', 'Aromatic basmati rice with spiced chicken and herbs.', 199.99),
(3, 'Caesar Salad', 'Crisp lettuce with Caesar dressing, croutons, and parmesan.', 149.99),
(4, 'Chocolate Lava Cake', 'Warm molten chocolate cake with a gooey center.', 99.99),
(5, 'Cold Coffee', 'Chilled coffee with a creamy, rich texture.', 89.99);

-- --------------------------------------------------------

-- Table: Orders
-- This table stores the orders placed by users.

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_details` text NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Sample Order Records
INSERT INTO `orders` (`id`, `user_id`, `order_details`, `total_price`, `order_date`) VALUES
(1, 1, '1x Margherita Pizza, 1x Caesar Salad', 449.98, '2024-11-20 15:26:31'),
(2, 2, '2x Chicken Biryani, 1x Cold Coffee', 489.98, '2024-11-20 15:26:31'),
(3, 3, '1x Chicken Biryani, 1x Chocolate Lava Cake', 299.98, '2024-11-20 15:26:31'),
(4, 1, '1x Caesar Salad, 1x Cold Coffee', 239.98, '2024-11-20 15:26:31');

-- --------------------------------------------------------

-- Table: Users
-- This table stores registered users for the application.

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL UNIQUE,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Sample User Records
INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'John Doe', 'john@example.com', 'password123'),
(2, 'Jane Smith', 'jane@example.com', 'securepassword'),
(3, 'Mike Brown', 'mike@example.com', 'mikepassword');

-- --------------------------------------------------------

-- Indexes and Constraints
-- Adding Primary Keys and Foreign Keys
ALTER TABLE `menuitems`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

-- Auto-increment settings for IDs
ALTER TABLE `menuitems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

COMMIT;
