-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 11 Nov 2020 pada 11.13
-- Versi Server: 10.0.17-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flick`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `confirmation_token`
--

CREATE TABLE `confirmation_token` (
  `token_id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `confirmation_token`
--

INSERT INTO `confirmation_token` (`token_id`, `confirmation_token`, `created_date`, `user_id`) VALUES
(9, 'a03fefb9-b0e4-44e8-aa39-d43eaf1dfb70', '2020-11-11 13:00:29', 7),
(8, '114e88dd-c4fc-48ef-9567-42f301e6ca04', '2020-11-11 12:59:46', 7),
(11, '3c1589ab-c4d8-4660-a198-0af863aefede', '2020-11-11 13:02:41', 10),
(12, '23e8e26b-2186-4039-8657-b57d3e0c2de5', '2020-11-11 13:03:25', 10),
(14, 'ed1a70a7-6257-4754-bcea-58321da94d0d', '2020-11-11 13:09:14', 13),
(16, '7b28ec58-3267-46f3-a998-6090551c6ade', '2020-11-11 15:34:05', 15);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(17),
(17);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `hak_akses` varchar(255) DEFAULT NULL,
  `nama_lengkap` varchar(255) DEFAULT NULL,
  `no_telp` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`user_id`, `email_id`, `enabled`, `hak_akses`, `nama_lengkap`, `no_telp`, `password`, `status`, `username`) VALUES
(13, 'jagatraya.gentar@gmail.com', 1, 'user', 'users', '0219219', '$2a$10$abTZUm8xYEs7cPjn0X/q1.D3M3ZKtkjNCRRtiIfVKLZnGkYdZ386O', 'belum aktif', 'user'),
(10, 'sayagentar@gmail.com', 1, 'admin', 'gentar', '023131', '$2a$10$zNECGMOk3.4n3FfGO4eEh.3.YCmxqKv.vNWIDRRMug1v39DaBbghy', 'aktif', 'admin'),
(15, 'sayagsentar@gmail.com', 0, 'user', 'asasa', '023131', '$2a$10$CbQ5.2Is4CdZO0q8RpKa8eAXslrCexnwVCYSI43HsDfhyF6/1HxOS', 'belum aktif', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`token_id`),
  ADD KEY `FKah4p1rycwibwm6s9bsyeckq51` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
