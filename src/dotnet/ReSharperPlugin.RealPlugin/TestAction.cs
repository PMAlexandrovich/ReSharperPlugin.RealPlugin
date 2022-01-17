using JetBrains.Annotations;
using JetBrains.Application.DataContext;
using JetBrains.Application.UI.Actions;
using JetBrains.Application.UI.Actions.MenuGroups;
using JetBrains.Application.UI.ActionsRevised.Menu;
using JetBrains.Application.UI.ActionSystem.ActionsRevised.Menu;
using JetBrains.ProjectModel;
using JetBrains.Rd.Base;
using JetBrains.ReSharper.Host.Features;
using JetBrains.Rider.Model;
using JetBrains.Util;

namespace ReSharperPlugin.RealPlugin
{
    public abstract class SampleAction : IExecutableAction
    {
        public bool Update(IDataContext context, ActionPresentation presentation, DelegateUpdate nextUpdate)
        {
            return true;    // function result indicates whether the menu item is enabled or disabled
        }
 
        public void Execute(IDataContext context, DelegateExecute nextExecute)
        {
            RunAction(context, nextExecute);
        }
 
        protected abstract void RunAction(IDataContext context, DelegateExecute nextExecute);        
    }
 
    [Action("ActionSendExtMessage", "Action Send Ext Message")]
    public class TestAction : SampleAction
    {
        protected override void RunAction(IDataContext context, DelegateExecute nextExecute)
        {
            var solution = context.GetData(JetBrains.ProjectModel.DataContext.ProjectModelDataConstants.SOLUTION);
            var protocolSolution = solution.GetProtocolSolution();
            var testModel = protocolSolution.GetTestModel();
            testModel.CQSFiles.Value = new []
            {
                new CQSFile("C:\\Users\\Максим\\RiderProjects\\ForPluginTest\\ConsoleApp1\\Program.cs",
                    new []
                    {
                        "C:\\Users\\Максим\\RiderProjects\\cargorun\\Carteam.Orleans\\Carteam.Silo.Server.Domain.Bids\\Commands\\ApplyCompoundBidCommand.cs"
                    }),
                new CQSFile("C:\\Users\\Максим\\RiderProjects\\cargorun\\Carteam.Orleans\\Carteam.Silo.Server.Domain.Bids\\Commands\\ApplyCompoundBidCommand.cs",
                    new []
                    {
                        "C:\\Users\\Максим\\RiderProjects\\YATRS\\src\\Smartpetrol.Yatrs.Web\\Startup.cs",
                        "C:\\Users\\Максим\\RiderProjects\\YATRS\\src\\Smartpetrol.Yatrs.Web\\Controllers\\Api\\UserApiKeyController.cs"
                    })
            };
        }
    }
}