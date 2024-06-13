-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 13, 2024 at 04:32 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recipeasy`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `comment_contents` varchar(255) DEFAULT NULL,
  `connected_id` int(11) NOT NULL,
  `post_time` timestamp NULL DEFAULT current_timestamp(),
  `commenter_id` int(11) DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `comment_contents`, `connected_id`, `post_time`, `commenter_id`, `recipe_id`) VALUES
(0, 'Comment removed by Admin', -1, '2024-06-12 14:14:23', 254, 152),
(5, 'This looks horrible!', -1, '2024-06-12 14:15:17', 254, 152),
(15, 'I am a comment worth being banned over!', -1, '2024-06-12 14:19:29', 255, 152),
(202, 'Thank you!', 0, '2024-06-12 14:15:13', 253, 152),
(203, 'Too bad', 0, '2024-06-12 14:58:18', 253, 152);

-- --------------------------------------------------------

--
-- Table structure for table `comment_seq`
--

CREATE TABLE `comment_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment_seq`
--

INSERT INTO `comment_seq` (`next_val`) VALUES
(301);

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

CREATE TABLE `favorites` (
  `favorite_id` int(11) NOT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `favorites_seq`
--

CREATE TABLE `favorites_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorites_seq`
--

INSERT INTO `favorites_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `ingredient_id` int(11) NOT NULL,
  `ingredient_measurment` varchar(255) DEFAULT NULL,
  `ingredient_name` varchar(255) DEFAULT NULL,
  `ingredient_quantity` float DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  `ingredient_amount` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`ingredient_id`, `ingredient_measurment`, `ingredient_name`, `ingredient_quantity`, `recipe_id`, `ingredient_amount`) VALUES
(202, NULL, 'Pork', NULL, 152, '200g'),
(203, NULL, 'Egg', NULL, 152, '1'),
(204, NULL, 'Water', NULL, 152, 'Dash'),
(205, NULL, 'Salt', NULL, 152, '1/2 tsp'),
(206, NULL, 'Sugar', NULL, 152, '10g'),
(207, NULL, 'Soy Sauce', NULL, 152, '10g'),
(208, NULL, 'Strach', NULL, 152, '30g'),
(209, NULL, 'Tomatoe Pure', NULL, 152, '10g'),
(210, NULL, 'Vinegar', NULL, 152, 'Dash'),
(211, NULL, 'Coriander', NULL, 152, '10g'),
(212, NULL, 'safhdj', NULL, 153, 'sajkdfh');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients_seq`
--

CREATE TABLE `ingredients_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients_seq`
--

INSERT INTO `ingredients_seq` (`next_val`) VALUES
(301);

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `recipe_id` int(11) NOT NULL,
  `recipe_country` varchar(255) DEFAULT NULL,
  `recipe_title` varchar(255) DEFAULT NULL,
  `recipe_type` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `total_saves` int(11) NOT NULL,
  `yield` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `recipe_image` varchar(255) DEFAULT NULL,
  `recipe_instructions` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`recipe_id`, `recipe_country`, `recipe_title`, `recipe_type`, `time`, `total_saves`, `yield`, `user_id`, `recipe_image`, `recipe_instructions`) VALUES
(152, 'Chinese', 'Sweet and Sour Pork', 'Pork', NULL, 0, NULL, 253, 'https://www.themealdb.com/images/media/meals/1529442316.jpg', 'Preparation\\r\\n1. Crack the egg into a bowl. Separate the egg white and yolk.\\r\\n\\r\\nSweet and Sour Pork\\r\\n2. Slice the pork tenderloin into strips.\\r\\n\\r\\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\\r\\n\\r\\n4. Marinade the pork strips for about 20 minutes.\\r\\n\\r\\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\\r\\n\\r\\nSweet and Sour Pork\\r\\nCooking Instructions\\r\\n1. Pour the cooking oil into a wok and heat to 190°C (375°F). Add the marinated pork strips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\\r\\n\\r\\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\\r\\n\\r\\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork strips to it.\\r\\n\\r\\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\\r\\n\\r\\n5. Serve on a plate and add some coriander for decoration.'),
(153, 'jkhasdf', 'nasjkdf', 'jkhsadf', NULL, 0, NULL, 253, 'https://www.themealdb.com/images/media/meals/pkopc31683207947.jpg', 'jhksadfhlj');

-- --------------------------------------------------------

--
-- Table structure for table `recipe_seq`
--

CREATE TABLE `recipe_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipe_seq`
--

INSERT INTO `recipe_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL,
  `report_reason` varchar(255) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reports_seq`
--

CREATE TABLE `reports_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports_seq`
--

INSERT INTO `reports_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active_user` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `preference` varchar(255) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `is_active_user`, `password`, `preference`, `role`, `username`) VALUES
(252, 'email@email.com', b'1', '$2a$10$rOTNggajanuVvzZBbjMVXOjMQH2ol6ta2yeb36wXxHYKs.5Pu5cZe', '', 2, 'admin'),
(253, 'email@email.com', b'1', '$2a$10$kQH4XL1impVIz3jwUBqe8OJYHlnhgjiXvErP1iegpudu1se/AXdcy', '', 1, 'creator'),
(254, 'email@email.com', b'0', '$2a$10$ZX6LDyn4VZew6LBXSlg2neXjkNsInpFmOViXDetulsIljjFIOATBS', '', 0, 'user'),
(255, 'email@email.com', b'1', '$2a$10$rZNVbaxYCdx5gT0XdTU2xOwUSqJlTZCn2nEGjTeDSNMXuGO00MDGu', '', 0, 'bannableuser'),
(256, 'grape@gmail.com', b'1', '$2a$10$HG2VRgOFNdZlNp59o2s2yOPHMrh.V/3bVaNe8gWWhtEABRjPeExga', 'Cake', 0, 'grape');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(351);

-- --------------------------------------------------------

--
-- Table structure for table `user_stats`
--

CREATE TABLE `user_stats` (
  `user_stat_id` int(11) NOT NULL,
  `timestamp` timestamp NULL DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_stats_seq`
--

CREATE TABLE `user_stats_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_stats_seq`
--

INSERT INTO `user_stats_seq` (`next_val`) VALUES
(1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FK44qghiqgaeg9imb7fq2ukffy1` (`commenter_id`),
  ADD KEY `FKe5i1rxybcm40jcn98fj1jmvit` (`recipe_id`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`favorite_id`),
  ADD KEY `FKdc2krcl0goons30jlgtyl9ra` (`recipe_id`),
  ADD KEY `FK1uphh0glinnprjknyl68k1hw1` (`user_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`ingredient_id`),
  ADD KEY `FKiau49hpb0ahqg8r9mi42deywl` (`recipe_id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`recipe_id`),
  ADD KEY `FKc8o8io8s0f7nqcd3429u6cxjs` (`user_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `FKh9ef682g96y52hn1o8cn9oacc` (`comment_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_stats`
--
ALTER TABLE `user_stats`
  ADD PRIMARY KEY (`user_stat_id`),
  ADD UNIQUE KEY `UKcgfgfs7fk42h7ck71lrs42sou` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK44qghiqgaeg9imb7fq2ukffy1` FOREIGN KEY (`commenter_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKe5i1rxybcm40jcn98fj1jmvit` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`);

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `FK1uphh0glinnprjknyl68k1hw1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKdc2krcl0goons30jlgtyl9ra` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`);

--
-- Constraints for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD CONSTRAINT `FKiau49hpb0ahqg8r9mi42deywl` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`);

--
-- Constraints for table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `FKc8o8io8s0f7nqcd3429u6cxjs` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `FKh9ef682g96y52hn1o8cn9oacc` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`);

--
-- Constraints for table `user_stats`
--
ALTER TABLE `user_stats`
  ADD CONSTRAINT `FKh7w0kxe92n5s3h2jbs1cfqx9c` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
