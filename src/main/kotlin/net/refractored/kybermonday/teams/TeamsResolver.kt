package net.refractored.kybermonday.teams

import net.refractored.kybermonday.exceptions.CommandErrorException
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import revxrsal.commands.autocomplete.SuggestionProvider
import revxrsal.commands.command.CommandActor
import revxrsal.commands.command.ExecutableCommand
import revxrsal.commands.process.ValueResolver

class TeamsResolver :
    ValueResolver<Team>,
    SuggestionProvider {
    override fun resolve(context: ValueResolver.ValueResolverContext): Team {
        val tag = context.arguments().pop()
        return Teams.getTeam(tag) ?: run {
            throw CommandErrorException(
                "<red>Unknown Team.".toComponent(),
            )
        }
    }

    override fun getSuggestions(
        args: MutableList<String>,
        sender: CommandActor,
        command: ExecutableCommand,
    ): MutableCollection<String> = Teams.getTeams().map { it.id }.toMutableList()
}
