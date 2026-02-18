# Transactions Feature Module

The `features:transactions` module implements the transaction history and details functionality.

## Purpose
This module allows users to view their financial or activity transaction records.

## Contents
- **Screens**: `TransactionScreen` - UI implementation for listing and viewing transactions.
- **Navigation**: `TransactionsNavigation` - Route definitions and navigation for the transaction history.

## Implementation Details
- Uses **MVI (Model-View-Intent)** with a state-driven UI.
- Fetches data from `core:domain` repository implementations.
- Utilizes **Compose Multiplatform** for a shared list/detail UI.

## Usage
The transaction feature is typically accessible from the main bottom navigation bar or the home screen.
Depends on `:core:ui` and `:core:domain`.
