package me.fulln.domain.command;

public record CreateQuestionCommand(
        String questionId,
        String title,
        String description
) {
}
