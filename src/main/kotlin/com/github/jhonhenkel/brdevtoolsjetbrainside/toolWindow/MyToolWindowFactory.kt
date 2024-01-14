package com.github.jhonhenkel.brdevtoolsjetbrainside.toolWindow

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCnpjService
import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCpfService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.awt.GridLayout
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = BrDevToolsWindow(toolWindow)

        toolWindow.contentManager.addContent(
            ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        )
    }

    override fun shouldBeAvailable(project: Project) = true

    class BrDevToolsWindow(toolWindow: ToolWindow) {

        private val cpfService = toolWindow.project.service<RandomCpfService>()
        private val cnpjService = toolWindow.project.service<RandomCnpjService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {

            add(JBLabel("Ao clicar no botão, o valor será "))
            add(JBLabel("copiado para a área de transferência."))

            val panel = JBPanel<JBPanel<*>>()
            panel.setLayout(GridLayout(1,2))
            panel.withPreferredSize(350, 25)

            val randomCpf = JBLabel("")
            panel.add(JButton("CPF").apply {
                addActionListener {
                    randomCpf.text = cpfService.generateRandomCpf()
                    val cpf = StringSelection(randomCpf.text)
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    clipboard.setContents(cpf, null)
                }
            })
            panel.add(randomCpf)

            val panel2 = JBPanel<JBPanel<*>>()
            panel2.setLayout(GridLayout(1,2))
            panel2.withPreferredSize(350, 25)
            val randomCnpj = JBLabel("")
            panel2.add(JButton("CNPJ").apply {
                addActionListener {
                    randomCnpj.text = cnpjService.generateRandomCnpj()
                    val cpf = StringSelection(randomCnpj.text)
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    clipboard.setContents(cpf, null)
                }
            })
            panel2.add(randomCnpj)

            add(panel)
            add(panel2)
        }
    }
}
