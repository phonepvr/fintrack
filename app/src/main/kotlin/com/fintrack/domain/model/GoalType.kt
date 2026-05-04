package com.fintrack.domain.model

/**
 * Goal semantics. NET_WORTH goals compare against `Goal.targetNetWorth`;
 * DEBT_FREE goals are achieved when a snapshot has zero total liabilities.
 */
enum class GoalType { NET_WORTH, DEBT_FREE }
